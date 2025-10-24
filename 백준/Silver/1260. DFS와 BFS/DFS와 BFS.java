import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean [] dfsVisited;
    static boolean [] bfsVisited;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int v = arr[2];

        dfsVisited = new boolean[n + 1];
        bfsVisited = new boolean[n + 1];
        list = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList();
        }
        for (int i = 0; i < m; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[temp[0]].add(temp[1]);
            list[temp[1]].add(temp[0]);
        }

        for (int i = 0; i <= n; i++) {
            Collections.sort(list[i]);
        }

        dfs(v);
        System.out.println();
        bfs(v);

    }

    public static void dfs(int idx) {
        if (dfsVisited[idx]) return;
        System.out.print(idx  + " ");
        dfsVisited[idx] = true;

        for (Integer i: list[idx]) {
            dfs(i);
        }
    }

    public static void bfs(int idx) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(idx);
        bfsVisited[idx] = true;
        System.out.print(idx + " ");

        while(!queue.isEmpty()) {
            int target = queue.poll();

            for (Integer i: list[target]) {
                if (bfsVisited[i]) continue;
                queue.add(i);
                bfsVisited[i] = true;
                System.out.print(i + " ");

            }
        }
    }
}
