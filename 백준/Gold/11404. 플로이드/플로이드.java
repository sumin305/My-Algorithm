import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 100000 * N);
        }
        for (int i = 0; i < M; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = temp[0] - 1;
            int y = temp[1] - 1;
            int dis = temp[2];
            dp[x][y] = Math.min(dp[x][y], dis);
        }

        for (int i = 0; i < N; i++) dp[i][i] = 0;

        for (int k = 0; k < N; k++) { // 경유 
	        for (int i = 0; i < N; i++) { //출발 
	        	for (int j = 0; j < N; j++) { // 도착 
	        			dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
	        	}
	        }
        }
        
        for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (dp[i][j] == 100000 * N) dp[i][j] = 0;
    			System.out.print(dp[i][j] + " ");
        	}
    		System.out.println();
        }
    }
}