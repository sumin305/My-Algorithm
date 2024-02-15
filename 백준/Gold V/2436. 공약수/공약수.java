import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        long num = b / a;
        long minValue = Integer.MAX_VALUE;
        long[] result = new long[2];
        long i = 1;
        while (true) {
            if (num % i == 0) {
                long x = i;
                long y = num / i;
                if (x > y)
                    break;
                long sum = a * (x + y);
                if (sum < minValue) {
                    if (gcd(x * a, y * a) == a) {
                        minValue = sum;
                        result[0] = x * a;
                        result[1] = y * a;
                    }
                }
                if (x == y)
                    break;
            }
            i++;
        }
        System.out.println(result[0] + " " + result[1]);
    }

    private static long gcd(long l, long m) {
        if (m == 0)
            return l;
        return gcd(m, l % m);
    }
}