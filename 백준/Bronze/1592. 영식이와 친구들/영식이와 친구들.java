import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int target = 0, count = 0;
        arr[target] = 1;

        while (arr[target] < M) {
            if (arr[target] % 2 == 0) {
                if (target - L < 0) {
                    target = N - (L - target);
                } else {
                    target -= L;
                }
            } else {
                target = (target + L) % N;
            }
            arr[target] ++;
            count ++;
        }
        System.out.println(count);
    }
}
