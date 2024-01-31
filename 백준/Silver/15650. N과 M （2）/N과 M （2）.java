import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int[] numbers;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];
		numbers = new int[M];
		 
		// 1~N 자연수 중 중복 없이 M개 고른 수열 (수열은 오름차순) -> 조합 
		makeNums(0, 1);
	}
	
	public static void makeNums(int idx, int start) {
		if (idx == M) {
			for (int i: numbers) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		for (int num = start; num <= N; num++) {
			if (!visited[num]) {
				visited[num] = true;
				numbers[idx] = num;
				makeNums(idx + 1, num + 1);
				visited[num] = false;
			}
		}
	}
}