import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n) {
        int result = 0;
        if (n % 2 != 0) return 0;
        long[] dp = new long[n + 1];
        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 2; j < i - 2; j += 2) {
                dp[i] += (dp[j] * 2);
            }
            dp[i] = (dp[i] + 2) % 1000000007;
        }
        result = (int)dp[n];
        return result;
    }
}