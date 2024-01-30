import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static StringBuilder sb;
    static int[] array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        array = new int[M];
        makeNum(0);
        System.out.println(sb.toString());
    }

    public static void makeNum(int idx) {
        if (idx == M) {
            for (int i: array) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            array[idx] = i;
            makeNum(idx + 1);
        }
    }
}