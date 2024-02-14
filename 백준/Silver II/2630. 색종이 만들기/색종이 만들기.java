import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] papers;
	static int whiteCount;
	static int blueCount;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		papers = new int[N][N];
		for (int i = 0; i < N; i++) {
			papers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		count(N, 0, 0);
		System.out.println(whiteCount);
		System.out.println(blueCount);
	}
	
	public static void count(int n, int x, int y) {
		int sum = 0;
		for (int i = x; i < x + n; i++) for (int j = y; j < y + n; j++) sum += papers[i][j];
		if (sum == 0) {
			whiteCount++;
		} else if (sum == n * n) {
			blueCount++;
		} else {
			int half = n / 2;
			count(half, x, y);
			count(half, x + half, y);
			count(half, x, y + half);
			count(half, x + half, y + half);
		}
	}
}