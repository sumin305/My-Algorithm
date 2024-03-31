import java.io.*;
import java.util.*;

public class Solution {
	public static int N, W, H, minBrickCount;
	public static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {			
			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = tmp[0];
			W = tmp[1];
			H = tmp[2];
			int[][] map = new int[H][W];
			minBrickCount = H * W;
			for (int i = 0; i < H; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			
			for (int w = 0; w < W; w++) {
				int[][] cloneMap = cloneMap(map);
				DFS(0, w, cloneMap);
			}
			System.out.println("#" + tc + " " + minBrickCount);
		}

	}
	
	public static void DFS(int idx, int w, int[][] map) {

		if (idx == N) {
			int count = countBrickCount(map);
			minBrickCount = Math.min(minBrickCount, count);
			return;
		}
		
		int[][] cloneMap = cloneMap(map);

		int targetX = -1;

		// x좌표가 w인 위치에 구슬 떨어뜨린다.
		for (int x = 0; x < H; x++) {
			if (cloneMap[x][w] > 0) {
				targetX = x;
				break;
			}
		}
		
		if (targetX != -1) {
			bumb(targetX, w, cloneMap[targetX][w], cloneMap);
			drop(cloneMap);
			if (countBrickCount(cloneMap) == 0) {
				minBrickCount = 0;
				return;
			}
		} else { // 해당 y좌표 벽돌이 없을 경우 
			return;
		}
		
		for (int i = 0; i < W; i++) {
			DFS(idx + 1, i, cloneMap);	
		}
		map = cloneMap;
	}
	
	private static int countBrickCount(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) if (map[i][j] != 0) count ++;
		return count;
	}
	
	public static void bumb(int x, int y, int num, int[][] map) {
		map[x][y] = 0;
		 for (int i = 1; i < num; i++) {
			 for (int v = 0; v < 4; v++) {
				 int nx = x + (dx[v] * i);
				 int ny = y + (dy[v] * i);
				 
				 if (onMap(nx, ny) && map[nx][ny] > 0) {
					 bumb(nx, ny, map[nx][ny], map);
				 }
			 }
		 }
	}
	
	public static void drop(int[][] map) {
		
		for (int y = 0; y < W; y++) {
			Stack<Integer> stack = new Stack();
			for (int x = 0; x < H; x++) {
				if (map[x][y] > 0) {
					stack.push(map[x][y]);
				}
			}
			
			for (int x = H - 1; x >= 0; x--) {
				if (!stack.isEmpty()) {
					map[x][y] = stack.pop();
				} else {
					map[x][y] = 0;
				}
			}
		}
	}
	public static boolean onMap(int x, int y) {
		return (0 <= x && x < H && 0 <= y && y < W);
	}

	public static int[][] cloneMap(int[][] map) {
		
		int[][] cloneMap = new int[H][W];
		for (int i = 0; i < H; i++) cloneMap[i] = map[i].clone();
		return cloneMap;

	}
	public static void print(int[][] map) {
		System.out.println("맵 출력 시작");
		for (int i = 0; i < H; i++) System.out.println(Arrays.toString(map[i]));
		System.out.println("맵 출력 ");
	}
}
