import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            long budget = 0;
            long stockCount = 0;
            int[] price = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            long maxPrice = 0;
            for (int idx = N-1; idx >= 0; idx--) {
                if (price[idx] > maxPrice) {
                    budget += (stockCount * maxPrice);
                    maxPrice = price[idx];
                    stockCount = 0;
                } else {
                    budget -= price[idx];
                    stockCount ++;
                }
            }
            budget += (stockCount * maxPrice);
            System.out.println(budget);
        }
    }
}