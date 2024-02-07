import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int w;
	static int h;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) break;
			map = new int[h][w];
			visited = new boolean[h][w];
			int count = 0;
			
			for (int i = 0; i < h; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						count++;
						visited[i][j] = true;
						DFS(i, j);
					}
				}
			}
			System.out.println(count);
		}
	}
	
	public static void DFS(int x, int y) {
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (onMap(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
				visited[nx][ny] = true;
				DFS(nx, ny);
			}
		}
	}
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < h && 0 <= y && y < w);
	}
}