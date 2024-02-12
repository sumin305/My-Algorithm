import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static  void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        System.out.println(M - GCD(N, M));
    }

    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}