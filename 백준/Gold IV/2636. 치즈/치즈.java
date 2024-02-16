import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int day, cheeseCount, temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int A[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = A[0];
		M = A[1];
		map = new int[N][M];
		visited = new boolean[N][M];
		day = 0;
		cheeseCount = 0;

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					cheeseCount++;
			}
		}
		while (cheeseCount != 0) {
			visited = new boolean[N][M];
			day++;
			BFS();
		}
		System.out.println(day);
		System.out.println(temp);
		
	}

	static void BFS() {
		temp=cheeseCount;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0 });
		visited[0][0] = true;

		while (!q.isEmpty()) {

			int[] target = q.poll();
			int x = target[0];
			int y = target[1];

			if (target[2] == 1) {
				continue;
			}
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
					if (map[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, 0 });
					} else {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, 1 });
						map[nx][ny] = 0;
						cheeseCount--;
					}
				}
			}
		}
	}

}