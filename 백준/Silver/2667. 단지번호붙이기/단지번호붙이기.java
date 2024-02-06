import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {
		static int N;
		static int[][] map;
		static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		int count = 0;
		List<Integer> resultList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					count ++;
					int sum = BFS(i, j);
					resultList.add(sum);
				}
			}
		}
		System.out.println(count);
		Collections.sort(resultList);
		for (int i: resultList) {
			System.out.println(i);
		}
	}
	
	public static int BFS(int startX, int startY) {
		Queue<int []> q = new ArrayDeque<>();
		q.offer(new int[] {startX, startY});
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int total = 0;
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int x = target[0];
			int y = target[1];
			total ++;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (onMap(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}			
		}
		return total;
	}
	
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}
}