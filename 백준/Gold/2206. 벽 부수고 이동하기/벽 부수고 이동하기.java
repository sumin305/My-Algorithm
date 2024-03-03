import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    static long MIN_DISTANCE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = nm[0];
        M = nm[1];
        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) Arrays.fill(visited[i], -1);
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        BFS();
        if (MIN_DISTANCE == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(MIN_DISTANCE);
    }

    public static void BFS() {
        Queue<long[]> q = new ArrayDeque<>();
        q.add(new long[] {0, 0, 0, 1});
        visited[0][0] = 0;
        while (!q.isEmpty()) {
            long[] target = q.poll();
            long x = target[0];
            long y = target[1];
            long b = target[2];
            long dis = target[3];
            if (x == N - 1 && y == M - 1) {
                MIN_DISTANCE = Math.min(MIN_DISTANCE, dis);
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = (int) (x + dx[i]);
                int ny = (int) (y + dy[i]);

                if (!onMap(nx, ny)) continue;

                if (visited[nx][ny] == -1 && map[nx][ny] == 0) {
                    q.add(new long[] {nx, ny, b, dis + 1});
                    visited[nx][ny] = (int) b;
                } else if (visited[nx][ny] == -1 && map[nx][ny] == 1) {
                    if (b == 0) {
                        q.add(new long[] {nx, ny, 1, dis + 1});
                        visited[nx][ny] = 1;
                    }
                } else {
                    if (visited[nx][ny] > b) {
                        q.add(new long[] {nx, ny, map[nx][ny], dis + 1});
                        visited[nx][ny] = (int) map[nx][ny];
                    }
                }
            }
        }
    }

    public static boolean onMap(long x, long y) {
        return (0 <= x && x < N && 0 <= y && y < M);
    }
}