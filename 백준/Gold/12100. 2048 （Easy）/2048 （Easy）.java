import java.io.*;
import java.util.*;
public class Main {
	public static int N, map[][], maxBlockNum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		maxBlockNum = 0;
		for (int i = 0; i < N; i++) {
			 map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		dfs(0, map);
		System.out.println(maxBlockNum);
	}
	
	public static void dfs(int n, int[][] map) {
		if (n == 5) {
			maxBlockNum = Math.max(maxBlockNum, calMaxBlock(map));
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int[][] newMap = move(map, i);
			dfs(n + 1, newMap);
		}
	}
	
	public static int[][] move(int[][] map, int moveIdx) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) newMap[i] = map[i].clone();
		switch (moveIdx) {
		 // 오
		case 0:
			for (int i = 0; i < N; i++) {
				ArrayDeque<Integer> q = new ArrayDeque();
				int temp = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (newMap[i][j] == 0) continue;
					if (temp == newMap[i][j]) {
						q.removeLast();
						q.add(temp * 2);
						temp = 0;
					} else {
						q.add(newMap[i][j]);
						temp  = newMap[i][j];
					}
				}
				
				for (int j = N - 1; j >= 0; j--) {
					if (q.isEmpty()) newMap[i][j] = 0;
					else newMap[i][j] = q.poll();
				}
			}
			break;
			
		// 왼
		case 1:
			for (int i = 0; i < N; i++) {
				ArrayDeque<Integer> q = new ArrayDeque();
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if (newMap[i][j] == 0) continue;
					if (temp == newMap[i][j]) {
						q.removeLast();
						q.add(temp * 2);
						temp = 0;
					} else {
						q.add(newMap[i][j]);
						temp  = newMap[i][j];
					}
				}

				for (int j = 0; j < N; j++) {
					if (q.isEmpty()) newMap[i][j] = 0;
					else newMap[i][j] = q.poll();
				}
			}
			break;
			
		// 위
		case 2:
			for (int j = 0; j < N; j++) {
				ArrayDeque<Integer> q = new ArrayDeque();
				int temp = 0;
				for (int i = 0; i < N; i++) {
					if (newMap[i][j] == 0) continue;
					if (temp == newMap[i][j]) {
						q.removeLast();
						q.add(temp * 2);
						temp = 0;
					} else {
						q.add(newMap[i][j]);
						temp  = newMap[i][j];
					}
				}

				for (int i = 0; i < N; i++) {
					if (q.isEmpty()) newMap[i][j] = 0;
					else newMap[i][j] = q.poll();
				}
			}
			break;
		// 아래
		default:
			for (int j = 0; j < N; j++) {
				ArrayDeque<Integer> q = new ArrayDeque();
				int temp = 0;
				for (int i = N - 1; i >= 0; i--) {
					if (newMap[i][j] == 0) continue;
					if (temp == newMap[i][j]) {
						q.removeLast();
						q.add(temp * 2);
						temp = 0;
					} else {
						q.add(newMap[i][j]);
						temp  = newMap[i][j];
					}
				}
				
				for (int i = N - 1; i >= 0; i--) {
					if (q.isEmpty()) newMap[i][j] = 0;
					else newMap[i][j] = q.poll();
				}
			}

			break;
		}
		return newMap;
	}
	
	public static int calMaxBlock(int[][] map) {
		int maxNum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maxNum < map[i][j]) {
					maxNum = map[i][j];
				}
			}
		}
		return maxNum;
	}
}