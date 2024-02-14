import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;
	static int[][] maps;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		for (int i = 0; i < N; i++) maps[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		compress(N, 0, 0);
		System.out.println(sb.toString());
	}
	
	public static void compress(int n, int x, int y) {
		int sum = 0;
		for (int i = x; i < x + n; i++) for (int j = y; j < y + n; j++) sum += maps[i][j];
		if (sum == 0) {
			sb.append(0);
		} else if (sum == n * n) {
			sb.append(1);
		} else {
			sb.append("(");
			int half = n / 2;
			compress(half, x, y);
			compress(half, x, y + half);
			compress(half, x + half, y);
			compress(half, x + half, y + half);
			sb.append(")");
		}
	}
}