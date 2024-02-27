import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int[][][] dp = new int[N][N][3];

		dp[0][1][0] = 1;

		for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i = 2; i < N; i++) {
			if (map[0][i] == 0) dp[0][i][0] = dp[0][i - 1][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (map[i][j] != 0) continue;
				
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]; // i, j 좌표에 가로로 올 수 있는 경우의 수
				dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2]; // i, j 좌표에 세로로 올 수 있는 경우의 수
				
				if (map[i][j - 1] == 0 && map[i - 1][j] == 0) {
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}

		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}