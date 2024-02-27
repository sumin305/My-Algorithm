import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

        public static void main(String[] args) throws NumberFormatException, IOException {
            // TODO Auto-generated method stub
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N][3];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < N; i++) {
                int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = temp[0];
                int b = temp[1];
                int c = temp[2];

                if (i == 0) {
                    dp[i][0] = a;
                    dp[i][1] = b;
                    dp[i][2] = c;

                    continue;
                }

                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (j != k) dp[i][j] = Math.min(dp[i][j], temp[j] + dp[i - 1][k]);
                    }
                }

            }
            System.out.println(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
        }

    }