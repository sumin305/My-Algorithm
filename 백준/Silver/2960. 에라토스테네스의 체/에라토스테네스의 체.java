import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        int K = n[1];
        int count = 0;
        int MAX = N;
        boolean[] primeNum = new boolean[MAX + 1];

        for (int i = 0; i < primeNum.length; i++) {
            primeNum[i] = true;
        }

        primeNum[0] = primeNum[1] = false;

        for (int i = 2; i <= N; i++) {
            if (!primeNum[i]) continue;
            count++;

            if (count == K) {
                System.out.println(i);
                break;
            }

            if (primeNum[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (!primeNum[j]) continue;
                    count++;

                    primeNum[j] = false;
                    if (count == K) {
                        System.out.println(j);
                        break;
                    }
                }
            }
        }
    }
}