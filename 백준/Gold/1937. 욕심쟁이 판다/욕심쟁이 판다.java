import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int N, map[][], moveCnt[][], maxCnt;
	public static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		moveCnt = new int[N][N];
		visited = new boolean[N][N];
		maxCnt = 0;
		for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = true;
				dfs(i, j, i, j, 0);
				maxCnt = Math.max(maxCnt, moveCnt[i][j]);
			}
		}
//		for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
//		for (int i = 0; i < N; i++) System.out.println(Arrays.toString(moveCnt[i]));
		System.out.println(maxCnt + 1);
	}
	private static void dfs(int startX, int startY, int x, int y, int cnt) {
		int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
		
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!onMap(nx, ny) || map[nx][ny] <= map[x][y]) continue; // 맵을 벗어나거나 다음 칸의 대나무 양이 같거나 적을 경우 

			if (moveCnt[nx][ny] == 0) { // 방문안했으면 dfs 돌기 
				dfs(startX, startY, nx, ny, cnt + 1);
			
				moveCnt[x][y] = Math.max(moveCnt[x][y], moveCnt[nx][ny] + 1);
				if (moveCnt[x][y] > maxCnt) {
					maxCnt = moveCnt[x][y];
				}
			} else {
				moveCnt[startX][startY] = Math.max(moveCnt[startX][startY], moveCnt[nx][ny] + cnt);
				moveCnt[x][y] = Math.max(moveCnt[x][y], moveCnt[nx][ny] + 1);
				if (moveCnt[x][y] > maxCnt) {
					maxCnt = moveCnt[x][y];
				}
			}
		}
	}
	 
	private static boolean onMap(int i, int j) {
		return (0 <= i && i < N && 0 <= j && j < N);
	}

}
