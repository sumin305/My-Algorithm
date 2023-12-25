import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		StringTokenizer st;
		Arrays.fill(dp,  0);

		for (int i = 1; i <= N; i ++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());
			
			// 퇴사 전에 할 수 있는 상담일 경우,
			if (i + T - 1 <= N) {
				dp[i + T - 1] = Math.max(dp[i + T - 1], dp[i - 1] + P);
			}
			
			dp[i] = Math.max(dp[i], dp[i-1]);

		}
		System.out.println(dp[N]);
	}
}
