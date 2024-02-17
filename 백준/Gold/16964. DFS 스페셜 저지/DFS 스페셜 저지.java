import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, idx;
    static int result = 0;
    static HashSet<Integer>[] graphs;
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graphs = new HashSet[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graphs[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graphs[temp[0]].add(temp[1]);
            graphs[temp[1]].add(temp[0]);
        }

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        idx = 1; // 1은 탐색했으므로, 두번째 항목부터 탐색한다.
        visited[1] = true;
        if (arr[0] != 1) {
            System.out.println(0);
            return;
        }
        dfs(1);
        System.out.println(result);
    }

    public static void dfs(int num) {
        boolean allVisited = true;
        if (idx == N) {
            result = 1;
            return;
        }
        while (idx < N && graphs[num].contains(arr[idx])) {
            if (!visited[arr[idx]]) {
                int adj = arr[idx];
                idx ++;

                visited[adj] = true;
                dfs(adj);
                if (result == 1) break;
            }
        }
    }
}