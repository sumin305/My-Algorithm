import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 조합하기 위해 만든 변수들
	static int[] selectedEmptyIndex;
	static boolean[] visitedIndex;
	
	// 연구소 맵과 방문 여부 판단하는 배열
	static int[][] map;
	static boolean[][] visitedMap;
	
	static List<int[]> emptys;
	static int N, M, MAX_SAFE_AREA;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		emptys = new ArrayList<>();
		visitedMap = new boolean[N][M];
		map = new int[N][M];
		
		// map을 채우고 빈 칸의 좌표들을 기록한다.
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					emptys.add(new int[] {i, j});
				}
			}
		}
		
		visitedIndex = new boolean[emptys.size()];
		selectedEmptyIndex = new int[3];
		
		selectEmpty(0, 0);
		System.out.println(MAX_SAFE_AREA);
	}
	
	// 빈 칸 중, 3개를 고른다.
	public static void selectEmpty(int idx, int start) {
		// 3개를 다 고른 경우
		if (idx== 3) {
			int safeArea = makeWall(selectedEmptyIndex);
			MAX_SAFE_AREA = Math.max(MAX_SAFE_AREA, safeArea); // 각 케이스의 safe area로 MAX_SAFE_AREA를 갱신해준다.
			return;
		}
		
		for (int i = start; i < emptys.size(); i++) {
			if (!visitedIndex[i]) {
				visitedIndex[i] = true;
				selectedEmptyIndex[idx] = i;
				selectEmpty(idx + 1, i + 1);
				visitedIndex[i] = false;
			}
		}
	}
	
	public static int makeWall(int[] selectedEmptyIndex) {
		
		int[][] newMap = new int[N][M];
		boolean[][] newVisitedMap = new boolean[N][M];
		
		// 이 후 케이스들도 생각해줘야 하므로 배열을 복사해서 사용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
				newVisitedMap[i][j] = visitedMap[i][j];
			}
		}
		// 해당 조합에 해당하는 좌표에 벽을 세운다.
		for (int index: selectedEmptyIndex) {
			int[] coordinate = emptys.get(index);
			int x = coordinate[0];
			int y = coordinate[1];
			newMap[x][y] = 1;
		}
		
		// 바이러스 전파시킨다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!newVisitedMap[i][j] && newMap[i][j] == 2) {
					newVisitedMap[i][j] = true;
					BFS(i, j, newMap, newVisitedMap);
				}
			}
		}
		
		// 안전 영역 구한다.
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 0) count ++;
			}
		}
		return count;
	}
	
	public static void BFS(int startX, int startY, int[][] newMap, boolean[][] newVisitedMap) {
		
		 int[] dx = {-1, 1, 0, 0};
		 int[] dy = {0, 0, -1, 1};

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startX, startY});
		
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int x = target[0];
			int y = target[1];
			
			for (int k = 0; k < 4; k ++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (onMap(nx, ny) && !newVisitedMap[nx][ny] && newMap[nx][ny] == 0) {
					newMap[nx][ny] = 2;
					newVisitedMap[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < N & 0 <= y && y < M);
	}
}