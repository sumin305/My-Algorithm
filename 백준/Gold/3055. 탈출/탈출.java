import java.io.*;
import java.util.*;

public class Main {
	public static char[][] map;
	public static int[][] waterMap;
	public static List<Point> waterList;
	public static int r, c;
	public static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	public static Point hedgehogP, beaverP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		r = rc[0];
		c = rc[1];
		waterList = new ArrayList();
		map = new char[r][c];
		waterMap = new int[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '*') waterList.add(new Point(i, j));
				if (map[i][j] == 'S') hedgehogP = new Point(i, j);
				if (map[i][j] == 'D') beaverP = new Point(i, j);
			}
		}
		waterBFS();
		int result = hedgehogBFS(hedgehogP);
		if (result == -1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	public static int hedgehogBFS(Point hedgehogP) {
		Queue<int[]> hedgehogQueue = new ArrayDeque();
		boolean[][] hedgehogVisited = new boolean[r][c];
		hedgehogQueue.add(new int[] {hedgehogP.x, hedgehogP.y, 0});
		hedgehogVisited[hedgehogP.x][hedgehogP.y] = true;
		while (!hedgehogQueue.isEmpty()) {
			int[] target = hedgehogQueue.poll();
			int x = target[0];
			int y = target[1];
			int d = target[2];
			
			// 비버와 만난 경우 
			if (x == beaverP.x && y == beaverP.y) {
				return d;
			}
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (onMap(nx, ny) && !hedgehogVisited[nx][ny] &&  (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
					if (waterMap[nx][ny] != 0 && d + 1 >= waterMap[nx][ny]) continue; // 물이 퍼질 예정인 공간일때, 고슴도치와 닿을 예정이면 가지 않는다.
					hedgehogVisited[nx][ny] = true;
					hedgehogQueue.add(new int[] {nx, ny, d + 1});
				}
			}
		}
		return -1;	
	}
	// 맵에 물이 퍼지는 시간을 기록
	public static void waterBFS() {
		Queue<Point> waterQueue = new ArrayDeque();
		boolean[][] waterVisited = new boolean[r][c];
		for (Point p: waterList) {
			waterQueue.add(p);
			waterVisited[p.x][p.y] = true;
		}
		
		while (!waterQueue.isEmpty()) {
			Point p = waterQueue.poll();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (onMap(nx, ny) && !waterVisited[nx][ny] &&  map[nx][ny] == '.') {
					waterVisited[nx][ny] = true;
					waterMap[nx][ny] = waterMap[x][y] + 1; 
					waterQueue.add(new Point(nx, ny));
				}
			}
		}
		waterMap[beaverP.x][beaverP.y] = 200;
	}
	
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < r && 0 <= y && y < c);
	}

    	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}	
	}
}
