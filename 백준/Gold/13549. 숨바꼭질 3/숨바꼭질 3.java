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
		int[] visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		while (!q.isEmpty()) {
			int[] target = q.poll();
			int num = target[0];
			int sec = target[1];
			
			if (num == k) {
				return sec;
			}
			
			if (visited[num] > sec) {
				visited[num] = sec;
                if (0 <= num * 2 && num * 2 <= 100000) q.add(new int[] {num * 2, sec});
				if (0 <= num - 1 && num - 1 <= 100000) q.add(new int[] {num - 1, sec + 1});
				if (0 <= num + 1 && num + 1 <= 100000) q.add(new int[] {num + 1, sec + 1});
			}
		}
		return -1;
	}
}