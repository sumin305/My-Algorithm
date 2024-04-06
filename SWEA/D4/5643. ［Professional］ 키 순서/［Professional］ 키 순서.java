import java.io.*;
import java.util.*;

public class Solution {
	
	public static int[][] compare;
	public static int N, M;
	public static Set<Integer>[] fromSet; 
	public static Set<Integer>[] toSet; 
	public static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			compare = new int[N][N];
			fromSet = new HashSet[N];
			toSet = new HashSet[N];
			
			for (int i = 0; i < N; i++) {
				fromSet[i] = new HashSet();
				toSet[i] = new HashSet();
			}
			int result = 0;
			for (int i = 0; i < M; i++) {
				int[] ab = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int a = ab[0], b = ab[1];
				compare[a - 1][b - 1] = 1;
			}
			for (int i = 0; i < N; i++) {
				visited = new boolean[N];
				calNode(i, i);
			}

			// 해당 노드의 from + in 수가 N - 1이면 통과 
			for (int i = 0; i < N; i++) {		
				int from = fromSet[i].size();
				int to = toSet[i].size();
				if (from + to == N - 1) {
					result ++;
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

	private static void calNode(int t, int start) {
		for (int i = 0; i < N; i++) {
			if (compare[start][i] == 1 && !visited[i]) {
				fromSet[i].add(t);
				toSet[t].add(i);
				visited[start] = true;
				calNode(t, i);
			}
		}
	}

}