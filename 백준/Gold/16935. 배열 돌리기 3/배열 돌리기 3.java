import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int R;
    static int O;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] O = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int o: O) {
            switch (o) {
                case 1:
                    op1();
                    break;
                case 2:
                    op2();
                    break;
                case 3:
                    op3();
                    break;
                case 4:
                    op4();
                    break;
                case 5:
                    op5();
                    break;
                case 6:
                    op6();
                    break;
            }
        }
        printMap();
    }

    public static void op1() {
        Stack<Integer>[] colStack = new Stack[M];
        for (int j = 0; j < M; j++) {
            colStack[j] = new Stack<Integer>();
        }
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                colStack[j].push(map[i][j]);
            }
        }
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = colStack[j].pop();
            }
        }
    }
    public static void op2() {
        Stack<Integer>[] rowStack = new Stack[N];
        for (int i = 0; i < N; i++) {
            rowStack[i] = new Stack<Integer>();
        }
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                rowStack[i].push(map[i][j]);
            }
        }
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = rowStack[i].pop();
            }
        }
    }
    public static void op3() {
        int[][] newMap = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[N - 1 - j][i];
            }
        }
        int temp = M;
        M = N;
        N = temp;
        map = newMap;
    }
    public static void op4() {
        int[][] newMap = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[j][M - 1 - i];
            }
        }
        int temp = M;
        M = N;
        N = temp;
        map = newMap;
    }
    public static void op5() {
        int halfX = N / 2;
        int halfY = M / 2;
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        int start[][] = {{0, 0},
                {0, 1},
                {1, 1},
                {1, 0}};
        int[][] newMap = new int[N][M];
        for (int k = 0; k < 4; k++) {
            int startX = start[k][0] * halfX;
            int startY = start[k][1] * halfY;
            for (int i = 0; i < halfX; i++) {
                for (int j = 0; j < halfY; j++) {
                    newMap[startX + i + dx[k] * halfX][startY + j + dy[k] * halfY] = map[startX + i][startY + j];
                }
            }
        }
        map = newMap;
    }
    public static void op6() {
        int halfX = N / 2;
        int halfY = M / 2;
        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};
        int start[][] = {{0, 0},
                {1, 0},
                {1, 1},
                {0, 1}
        };
        int[][] newMap = new int[N][M];
        for (int k = 0; k < 4; k++) {
            int startX = start[k][0] * halfX;
            int startY = start[k][1] * halfY;
            for (int i = 0; i < halfX; i++) {
                for (int j = 0; j < halfY; j++) {
                    newMap[startX + i + dx[k] * halfX][startY + j + dy[k] * halfY] = map[startX + i][startY + j];
                }
            }
        }
        map = newMap;
    }

    public static void printMap() {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(map).forEach(h -> {
            Arrays.stream(h).forEach(w -> {
                sb.append(w).append(" ");
            });
            sb.append("\n");
        });
        System.out.println(sb.toString());
    }
}