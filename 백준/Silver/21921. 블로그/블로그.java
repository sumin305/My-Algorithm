import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NX = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int X = NX[1];
        int N = NX[0];
        int maxVisitorCount;
        int range = 1;
        int sum = 0;
        int[] visitors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < X; i++) sum += visitors[i];
        maxVisitorCount = sum;

        for (int i = X; i < N; i++) {
            sum -= visitors[i - X];
            sum += visitors[i];

            // 가장 큰 값인지 확인
            if (maxVisitorCount < sum) {
                maxVisitorCount = sum;
                range = 1;
            } else if (maxVisitorCount == sum) {
                range++;
            }
        }

        if (maxVisitorCount == 0) {
            System.out.println("SAD");
            return;
        }

        System.out.println(maxVisitorCount);
        System.out.println(range);
    }
}