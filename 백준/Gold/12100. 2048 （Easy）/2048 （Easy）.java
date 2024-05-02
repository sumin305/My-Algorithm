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
		int start = 0, end = 0, step = 0;
		switch (moveIdx) {
		case 0: case 3: 
			start = N - 1;
			end = -1;
			step = -1;
			break;	
		case 1: case 2:
			start = 0;
			end = N;
			step = 1;
			break;
		}

		for (int n = 0; n < N; n++) {
			ArrayDeque<Integer> q = new ArrayDeque();
			int temp = 0;
			for (int m = start; m != end; m += step) {
				int num = 0;
				if (moveIdx == 0 || moveIdx == 1) num = newMap[n][m];
				else num = newMap[m][n];
				if (num == 0) continue;
				if (temp == num) {
					q.removeLast();
					q.add(temp * 2);
					temp = 0;
				} else {
					q.add(num);
					temp  = num;
				}
			}

			for (int m = 0; m < N; m++) {
				if (moveIdx == 0 || moveIdx == 1) {
					if (q.isEmpty()) newMap[n][m] = 0;
					else newMap[n][m] = q.poll();
				} else {
					if (q.isEmpty()) newMap[m][n] = 0;
					else newMap[m][n] = q.poll();
				}
			}
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
