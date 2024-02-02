
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Bomb {
		int x;
		int y;
		int lastTime;
		
		public Bomb(int x, int y, int lastTime) {
			super();
			this.x = x;
			this.y = y;
			this.lastTime = lastTime;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getLastTime() {
			return lastTime;
		}

		public void setLastTime(int lastTime) {
			this.lastTime = lastTime;
		}
		
	}
	static int R;
	static int C;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int time = 1;
		List<int[]> bombList = new ArrayList<>();
		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] arr = br.readLine().toCharArray();
			for ( int j = 0 ; j < C ; ++j) {
				if ( arr[j] == 'O' ) {
					map[i][j] = 2;
				} else {
					map[i][j] = 0;
				}
			}
		}

		while (time < N) {
			bombList = new ArrayList<>();
			for (int i = 0; i < R; i++) {
				for ( int j = 0 ; j < C ; ++j) {
					if (map[i][j] == 0) {
						map[i][j] = 3;
					} else if (map[i][j] >= 1) {
						map[i][j] -= 1;
					}
					if (map[i][j] == 0) bombList.add(new int[] {i, j});
				}
			}
			for (int[] bomb: bombList) {
				for (int k = 0; k < 4; k++) {
					int nx = bomb[0] + dx[k];
					int ny = bomb[1] + dy[k];
					
					if (onMap(nx, ny) && map[nx][ny] >= 1) {
						map[nx][ny] = 0;
					}
				}
			}

			time ++;

		}
		

		for (int i = 0; i < R; i++) {
			for (int j = 0; j <C; j++) {
				if (map[i][j] >= 1) System.out.print("O");
				else System.out.print(".");
			}
			System.out.println();
		}
				
	}
	
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < R && 0 <= y && y < C); 
	}
}
