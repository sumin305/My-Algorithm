import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int result = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 1});
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] target = queue.poll();
			int x = target[0];
			int y = target[1];
			int distance = target[2];
			
			if (x == N-1 && y == M-1) {
				result = distance;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (onMap(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny, distance + 1});
				}
			}
		}
		System.out.println(result);
	}
	
	public static boolean onMap(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}
}