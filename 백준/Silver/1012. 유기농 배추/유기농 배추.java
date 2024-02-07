import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int N;
	static int M;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			int count = 0;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			
			// 배열 탐색하면서 배추가 있으면, 상하좌우 탐색 (DFS)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						count++;
						DFS(i, j);
					}
				}
			}
			System.out.println(count);
		}
	}

	public static void DFS(int x, int y) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for (int vector = 0; vector < 4; vector++) {
			int nx = x + dx[vector];
			int ny = y + dy[vector];
			if (onMap(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
				visited[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}
	
	public static boolean onMap(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
}