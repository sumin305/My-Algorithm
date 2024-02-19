import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public  static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        List<Integer> result = new ArrayList<>();
        int[] graph = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }
        boolean[] r = new boolean[N + 1];
        for (int i = 1; i < N+1; i++) {
            if (r[i]) continue;
            int start = i;
            int target;
            List<Integer> temp = new ArrayList<>();
            boolean[] visited = new boolean[N + 1];
            visited[start] = true;
            temp.add(start);
            target = graph[start];
            if (target == start) {
                cnt++;
                for (int t: temp) {
                    r[t] = true;
                    result.add(t);
                }
            }

            while (!visited[target]) {
                visited[target] = true;
                temp.add(target);
                target = graph[target];
                if (target == start) {
                    for (int t: temp) {
                        result.add(t);
                        cnt++;
                        r[t] = true;
                    }
                    break;
                }
            }
        }
        System.out.println(cnt);
        Collections.sort(result);
        for (int re: result) {
            System.out.println(re);
        }
    }
}
