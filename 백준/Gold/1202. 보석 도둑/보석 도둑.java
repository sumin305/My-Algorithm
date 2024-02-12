import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int K = arr[1];
        long result = 0;
        int[][] mv = new int[N][2];

        int[] C = new int[K];

        for (int i = 0; i < N; i++) {
            int[] temp = new int[2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            mv[i] = temp;
        }

        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(mv, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        Arrays.sort(C);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;

        for (int c: C) {
            while (idx < N) {
                if (mv[idx][0] <= c) {
                    pq.add(mv[idx][1]);
                    idx++;
                } else {
                    break;
                }
            }
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        System.out.println(result);
    }
}