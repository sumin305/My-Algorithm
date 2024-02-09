import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[][] operations;
    static int[] targetOperationIndex;
    static boolean[] visitedOperationIndex;
    static int MIN_RESULT = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        input();
        selectOperation(0);
        System.out.println(MIN_RESULT);
    }

    public static int calculateValue(int[][] newMap) {
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum[i] += newMap[i][j];
            }
        }
        return Arrays.stream(sum).min().getAsInt();
    }

    public static void rotation(int r, int c, int s, int[][] newMap) {
        int startX = r - 1 - s, startY = c - 1 - s;
        for (int rotate = s; rotate >= 1; rotate--) {
            int currentX = startX, currentY = startY;
            int before = newMap[currentX][currentY];
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};
            for (int vector = 0; vector < 4; vector ++) {
                for (int count = 0; count < 2 * rotate; count++) {
                    currentX += dx[vector];
                    currentY += dy[vector];
                    int temp = newMap[currentX][currentY];
                    newMap[currentX][currentY] = before;
                    before = temp;
                }
            }
            startX ++;
            startY ++;
        }
    }

    // 순열로 연산 순서를 정한다.
    public static void selectOperation(int idx) {
        if (idx == operations.length) {
            // 순열로 연산 순서 다 정했을때 회전한다.
            int[][] newMap = new int[N][M];
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < M; j++) {
                    newMap[i][j] = map[i][j];
                }
            }
            for (int opIndex: targetOperationIndex) {
                rotation(operations[opIndex][0], operations[opIndex][1], operations[opIndex][2], newMap);
                // 배열의 값을 정한다.
            }

            int minSum = calculateValue(newMap);
            MIN_RESULT = Math.min(minSum, MIN_RESULT);
            return;
        }
        for (int i = 0; i < K; i++) {
            if (!visitedOperationIndex[i]) {
                visitedOperationIndex[i] = true;
                targetOperationIndex[idx] = i;
                selectOperation(idx + 1);
                visitedOperationIndex[i] = false;
            }
        }
    }
    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        operations = new int[K][3];
        for (int i = 0; i < K; i++) {
            operations[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        targetOperationIndex = new int[K];
        visitedOperationIndex = new boolean[K];
    }
}