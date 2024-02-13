import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		dp[0] = 5001;
		dp[1] = 5001;
		dp[2] = 5001;
		dp[3] = 1;
		if (N >= 4) {
			dp[4] = 5001;
		}
		if (N >= 5) {
			dp[5] = 1;
		}
		if (N >= 6) {
			for (int i = 6; i <= N; i++) {
				dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
			}
		}
		if (dp[N] > 5000) System.out.println(-1);
		else System.out.println(dp[N]);
	}
}