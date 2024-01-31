import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int[] numbers;
	public static int[] target;
	public static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		while (!(s = br.readLine()).equals("0")) {
			StringTokenizer st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken());
			numbers = new int[N];
			target = new int[6];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			makeNumbers(0, 0);
			System.out.println();
		}
	}
	
	public static void makeNumbers(int idx, int start) {
		if (idx == 6) {
			for(int i: target) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				target[idx] = numbers[i];
				makeNumbers(idx + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}