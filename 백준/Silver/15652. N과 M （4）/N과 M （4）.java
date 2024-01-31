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
		
		// 1부터 N 까지 자연수 중에서 M개를 고른 수열 / 같은 수를 여러 번 골라도 된다.
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
			numbers[idx] = num;
			makeNums(idx + 1, num);
		}
	}
}