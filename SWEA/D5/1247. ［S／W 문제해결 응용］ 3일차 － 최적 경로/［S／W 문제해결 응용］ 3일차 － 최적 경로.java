import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static boolean[] visited;
    static int[][] consumer;
    static int min_distance = 0;
    static int home_x;
    static int home_y;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            min_distance = Integer.MAX_VALUE;
            consumer = new int[N][2];
            visited = new boolean[N];
            int company_x = Integer.parseInt(st.nextToken());
            int company_y = Integer.parseInt(st.nextToken());
            home_x = Integer.parseInt(st.nextToken());
            home_y = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N; i ++) {
                consumer[i][0] = Integer.parseInt(st.nextToken());
                consumer[i][1] = Integer.parseInt(st.nextToken());
            }
            per(0, 0, company_x, company_y);
            System.out.println("#" + test_case + " " + min_distance);
        }
    }

    public static void per(int idx, int distance, int current_x, int current_y) {
        if (idx == N) {
            distance += Math.abs(home_x - current_x) + Math.abs(home_y - current_y);
            min_distance = Math.min(min_distance, distance);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nextDistance = distance + Math.abs(current_x - consumer[i][0]) + Math.abs(current_y - consumer[i][1]);
                per(idx + 1, nextDistance, consumer[i][0], consumer[i][1]);
                visited[i] = false;
            }
        }
    }
}
