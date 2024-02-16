import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] mn = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = mn[0], N = mn[1];
		int answer = -1;
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int unripeTomatoCount = 0;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j];
				if (temp[j] == 0) unripeTomatoCount++;
				if (temp[j] == 1) {
					q.add(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int x = target[0];
			int y = target[1];
			int time = target[2];
			if (time != 0) unripeTomatoCount -= 1;
			
			if (unripeTomatoCount == 0) {
				answer = time;
				break;
			}
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					map[x][y] = 1;
					q.add(new int[] {nx, ny, time + 1});
				}
			}
		}		
		System.out.println(answer);
	}
}