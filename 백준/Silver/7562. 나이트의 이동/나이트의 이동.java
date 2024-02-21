import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int I, target_x, target_y, result;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			I = Integer.parseInt(br.readLine());
			map = new int[I][I];
			visited = new boolean[I][I];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			target_x = Integer.parseInt(st.nextToken());
			target_y = Integer.parseInt(st.nextToken());
			visited[x][y] = true;
			BFS(x, y);
			System.out.println(result);
		}
	}
	
	public static void BFS(int start_x, int start_y) {
		int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
		int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {start_x, start_y, 0});
		
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int x = target[0];
			int y = target[1];
			int count = target[2];
			
			if (x == target_x && y == target_y) {
				result = count;
				return;
			}
			
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < I && 0 <= ny && ny < I && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny, count + 1});
				}
			}
		}
	}
}