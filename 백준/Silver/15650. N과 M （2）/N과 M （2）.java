import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static boolean[] visited;
    public static int N, M, numbers[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = arr[0];
        M = arr[1];
        visited = new boolean[N + 1];
        numbers = new int[M];

        combination(0, 1);
    }

    // N개중에 M개 뽑는 조합
    public static void combination(int idx, int start) {

        if (idx == M) {
            for (int num: numbers) System.out.print(num + " ");
            System.out.println();
            return;
        }
        for (int num = start; num <= N; num++) {

            if (!visited[num]) {
                visited[num] = true;
                numbers[idx] = num;
                combination(idx + 1, num + 1);
                visited[num] = false;
            }
        }
    }
}
