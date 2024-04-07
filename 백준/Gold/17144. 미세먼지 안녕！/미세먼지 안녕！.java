import java.io.*;
import java.util.*;

public class Main {
    
	public static int[][] map;
	public static List<Dust> dusts;
	public static int R, C, T;
	public static int[] airCleanerTop, airCleanerBottom;
	public static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		R = temp[0];
		C = temp[1];
		T = temp[2];
		map = new int[R][C];
		dusts = new ArrayList();
		
		for (int i = 0; i < R; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
        // 공기청정기 위치 파악
		for (int i = 0; i < R; i++) {
			if (map[i][0] == -1) {
				airCleanerTop = new int[] {i, 0};
				airCleanerBottom = new int[] {i + 1, 0};
				break;
			}
		}
		
		for (int t = 1; t <= T; t++) {
			setDustList(); // dustList 생성
			spreadDust(); // 미세먼지 퍼진다
			OperateAirCleaner(true); // 위쪽 공기청정기 작동
			OperateAirCleaner(false); // 아래쪽 공기청정기 작동
		}
		
		System.out.println(calDust()); // 미세먼지 수 계산한 값 출력
	}

	private static void setDustList() {
		dusts = new ArrayList(); 
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {				
				if (map[i][j] > 0) {
					dusts.add(new Dust(i, j, map[i][j]));
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void OperateAirCleaner(boolean top) {
		int[] dx = new int[4];
		int[] dy = new int[] {1, 0, -1, 0};
		int tX, tY;
		// 위쪽 공기청정기일 경우
		if (top) {
			dx = new int[] {0, -1, 0, 1};
			tX = airCleanerTop[0];
			tY = airCleanerTop[1];
		} else { // 아래쪽 공기청정기일 경우
			dx = new int[] {0, 1, 0, -1};
			tX = airCleanerBottom[0];
			tY = airCleanerBottom[1];
		}
      
		Queue<Integer> q = new ArrayDeque();
		q.add(0); // 공기청정기에서 나오는 미세먼지는 0
        
		for (int v = 0; v < 4; v++) {
            
			tX += dx[v];
			tY += dy[v];
			
			while (true) {
				
				if (!onMap(tX, tY)) {
					tX -= dx[v]; // 이동할 수 없으므로 뒤로 이동
					tY -= dy[v];
					break;
				}
				
				if (map[tX][tY] == -1) return;
				q.add(map[tX][tY]);

				if (!q.isEmpty()) {
					map[tX][tY] = q.poll();
  				}
				
				tX += dx[v]; // 이동
				tY += dy[v];
			}
		}
	}
	
	private static void spreadDust() {
		for (Dust dust: dusts) {
			int count = 0;
			for (int v = 0; v < 4; v++) {
				int nx = dust.x + dx[v];
				int ny = dust.y + dy[v];
				
				if (onMap(nx, ny) && map[nx][ny] != -1) {
					count++;
					map[nx][ny] += (dust.val / 5);
				} 
			}
			dust.val = dust.val - (count * (dust.val / 5));
		}
		for (Dust dust: dusts) {
			map[dust.x][dust.y] += dust.val;
		}
	}

	private static int calDust() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		return result;
	}

	private static boolean onMap(int x, int y) {
		return (0 <= x && x < R && 0 <= y && y < C);
	}
	
	private static class Dust {
		int x, y, val;

		public Dust(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Dust [x=" + x + ", y=" + y + ", val=" + val + "]";
		}
		
	}	
}
