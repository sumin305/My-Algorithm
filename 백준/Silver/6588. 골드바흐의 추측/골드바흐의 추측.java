import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int MAX = 1000000;
        int n;
        boolean[] primeNum = new boolean[MAX + 1];
        // 에라토스테네스의 체를 활용해서 1000000까지의 소수를 찾는다.
        for (int i = 0; i < primeNum.length; i++) {
            primeNum[i] = true;
        }

        primeNum[0] = primeNum[1] = false;

        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (primeNum[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    primeNum[j] = false;
                }
            }
        }

        for (int i = 2; i <= MAX; i++) {
            if (i % 2 == 0) {
                primeNum[i] = false;
            }
        }
        boolean success = false;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            for (int i = n - 1; i > n / 2 - 1; i--) {
                if (primeNum[i] && primeNum[n - i]) {
                    System.out.println(n + " = " + (n - i) + " + " + i);
                    success= true;
                    break;
                }
            }
        }
        if (!success) System.out.println("Goldbach's conjecture is wrong.");
    }
}