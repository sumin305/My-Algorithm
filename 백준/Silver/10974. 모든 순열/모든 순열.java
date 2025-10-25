import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int arr[] = new int[N];
            arr[0] = i;
            visited[i] = true;
            permutation(1, i, arr);
        }
    }

    public static void permutation(int idx, int num, int[] arr) {
//       System.out.println(idx +  " " + num + " " + Arrays.toString(arr)+ " " + Arrays.toString(visited));

        if (idx == N) {
            for (int a: arr) System.out.print(a + " ");
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr[idx] = i;
            permutation(idx + 1, i, arr);
            visited[i] = false;
        }

    }

}
