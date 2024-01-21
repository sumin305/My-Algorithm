import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] timeArray = new int[N][2];
        int count = 1;
        PriorityQueue<Integer> timeQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 0; j < 2; j++) {
                timeArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(timeArray, Comparator.comparingInt(index -> index[0]));

        for (int i = 0; i < N; i++) {
            // 큐에 있는 가장 빠른 종료 시간보다 빠를 경우 -> 방이 부족한 경우
            if (!timeQueue.isEmpty() && timeQueue.peek() > timeArray[i][0]) {
                count++;
            } else {
                timeQueue.poll();
            }
            timeQueue.offer(timeArray[i][1]);
        }
        System.out.println(count);
    }
}