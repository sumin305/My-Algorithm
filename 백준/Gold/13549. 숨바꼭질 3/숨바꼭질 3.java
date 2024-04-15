import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = nk[0], K = nk[1];
		System.out.println(BFS(N, K));
	}

	private static int BFS(int n, int k) {
		Queue<int[]> q = new ArrayDeque();
		q.add(new int[] {n, 0});
		boolean[] visited = new boolean[100001];
		
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int num = target[0];
			int sec = target[1];
			
			if (num == k) {
				return sec;
			}
			
			if (!visited[num]) {
				visited[num] = true;
				if (0 <= num * 2 && num * 2 <= 100000) q.add(new int[] {num * 2, sec});
				if (0 <= num - 1 && num - 1 <= 100000) q.add(new int[] {num - 1, sec + 1});
				if (0 <= num + 1 && num + 1 <= 100000) q.add(new int[] {num + 1, sec + 1});
			}
		}
		return -1;
	}
}
