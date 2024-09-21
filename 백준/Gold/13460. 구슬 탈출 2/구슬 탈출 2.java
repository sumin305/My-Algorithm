import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int N, M, minCount;
	public static char[][] map;
	public static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	public static boolean[][][][] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = NM[0];
		M = NM[1];
		minCount = Integer.MAX_VALUE;
		map = new char[N][M];
		check = new boolean[N][M][N][M];
		Marble r = new Marble();
		Marble b = new Marble();
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				
				if (map[i][j] == 'B') b.setMarble(i, j);
				if (map[i][j] == 'R') r.setMarble(i, j);

			}
		}
		
		Pair startPair = new Pair(r, b, 0);
		dfs(startPair);
		System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
	}
	
	public static void dfs(Pair pair) {
		if (pair.count > 10) return;
		
		if (map[pair.red.x][pair.red.y] == 'O') {
            if (map[pair.blue.x][pair.blue.y] != 'O') {
                minCount = Math.min(minCount, pair.count);
            }
			return;
		}
		
		check[pair.red.x][pair.red.y][pair.blue.x][pair.blue.y] = true;
		
		for (int v = 0; v < 4; v++) {
			Marble newRedMarble = lean(v, pair.red);
			Marble newBlueMarble = lean(v, pair.blue);
	       
			if (map[newBlueMarble.x][newBlueMarble.y] == 'O') {
				continue;
			}
			// 위치가 같은	 경우
			if (Marble.compareTo(newRedMarble, newBlueMarble)) {
				switch(v) {
				case 0:
					if (pair.red.y < pair.blue.y) newRedMarble.setMarble(newRedMarble.x, newRedMarble.y - 1);
					else newBlueMarble.setMarble(newBlueMarble.x, newBlueMarble.y - 1);
					break;
				case 1:
					if (pair.red.y > pair.blue.y) newRedMarble.setMarble(newRedMarble.x, newRedMarble.y + 1);
					else newBlueMarble.setMarble(newBlueMarble.x, newBlueMarble.y + 1);
					break;
				case 2: 
					if (pair.red.x < pair.blue.x) newRedMarble.setMarble(newRedMarble.x - 1, newRedMarble.y);
					else newBlueMarble.setMarble(newBlueMarble.x - 1, newBlueMarble.y);
					break;
				case 3:
					if (pair.red.x > pair.blue.x) newRedMarble.setMarble(newRedMarble.x + 1, newRedMarble.y);
					else newBlueMarble.setMarble(newBlueMarble.x + 1, newBlueMarble.y);
					break;
				}
			}
			
			if (!check[newRedMarble.x][newRedMarble.y][newBlueMarble.x][newBlueMarble.y]) {
				dfs(new Pair(newRedMarble, newBlueMarble, pair.count + 1));
			}
		}
	    check[pair.red.x][pair.red.y][pair.blue.x][pair.blue.y] = false; 

	}
	
	public static Marble lean(int vector, Marble marble) { // 동(0), 서(1), 남(2), 북(3)
		Marble newMarble = new Marble();
		int x = marble.x;
		int y = marble.y;
		
		while (map[x + dx[vector]][y + dy[vector]] != '#') {
			x += dx[vector];
			y += dy[vector];
			
			if (map[x][y] == 'O') break;
		}
		
		newMarble.setMarble(x, y);
		return newMarble;
	}
	
	public static class Marble {
		int x, y;

		public void setMarble(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public static boolean compareTo(Marble a, Marble b) {
			if (a.x == b.x && a.y == b.y) return true;
			return false;
		}

	}
	public static class Pair {
		Marble red;
		Marble blue;
		int count;
		
		public Pair (Marble r, Marble b, int c) {
			this.red = r;
			this.blue = b;
			this.count = c;
		}

	}
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < M);
	}
}
