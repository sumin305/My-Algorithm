import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static int[][] map;
	private static List<Point> blank;
	private static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		blank = new ArrayList();
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == 0) {
					blank.add(new Point(i, j));
				}
			}
		}
		dfs(0);
	}
	
	private static void dfs(int idx) {
		if (idx == blank.size()) {
			printMap();
			System.exit(0);
		}
		Point target = blank.get(idx); // 빈 칸 좌표
		for (int num = 1; num <= 9; num++) {
			if (isColOkay(target.x, target.y, num) 
					&& isRowOkay(target.x, target.y, num) 
					&& isSquareOkay(target.x, target.y, num)) {
				map[target.x][target.y] = num;
				dfs(idx + 1);
				map[target.x][target.y] = 0;
			}
		}		
	}
	private static void printMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static boolean isColOkay(int x, int y, int num) {
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == num) return false;
		}
		return true;
	}
	private static boolean isRowOkay(int x, int y, int num) {
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == num) return false;
		}
		return true;
	}
	private static boolean isSquareOkay(int x, int y, int num) {
		Point startP = getQuadrant(x, y);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[startP.x + i][startP.y + j] == num) return false;
			}
		}
		return true;
	}
	private static Point getQuadrant(int x, int y) {
		if (0 <= x && x <= 2) {
			if (0 <= y && y <= 2) {
				return new Point(0, 0);
			} else if (2 < y && y <= 5) {
				return new Point(0, 3);
			} else {
				return new Point(0, 6);
			}
		} else if (2 < x && x <= 5) {
			if (0 <= y && y <= 2) {
				return new Point(3, 0);
			} else if (2 < y && y <= 5) {
				return new Point(3, 3);
			} else {
				return new Point(3, 6);
			}
		} else {
			if (0 <= y && y <= 2) {
				return new Point(6, 0);
			} else if (2 < y && y <= 5) {
				return new Point(6, 3);
			} else {
				return new Point(6, 6);
			}

		}
	}
}