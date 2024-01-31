import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int M;
	static int count = 0; 
	static int depth = 0;
	static int MAX_DEPTH = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N  = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					count ++;
					depth ++;
					dfs(i, j);
					MAX_DEPTH = Math.max(MAX_DEPTH, depth);
					depth = 0;
					
				}
			}
		}
		System.out.println(count);
		System.out.println(MAX_DEPTH);
		
	}
	
	public static void dfs(int x, int y) {
		if (map[x][y] == 0) return;
		for (int i = 0; i < 4; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (onMap(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny);
					depth ++;
				}
			}
		}
	}

	public static boolean onMap(int x, int y) {
		if (x >= 0 && x < N &&  y >= 0 && y < M) {
			return true;
		}
		return false;
	}
}