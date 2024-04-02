import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = temp[0];
		int M = temp[1];
		int[][] map = new int[N + 1][N + 1];
		int[][] dp = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 1; j <= N; j++) {
				map[i][j] = temp[j - 1];
			}
		}
		
		dp[1][1] = map[1][1];
		for (int i = 2; i <= N; i++) {
			dp[i][1] = dp[i - 1][1] + map[i][1];
			dp[1][i] = dp[1][i - 1] + map[1][i];
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int x1 = temp[0], y1 = temp[1];
			int x2 = temp[2], y2 = temp[3];
			
			System.out.println(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]);
		}
	}

}