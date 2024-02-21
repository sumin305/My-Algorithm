import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[][] visited;
    static int MIN_BLIND_SPOT = Integer.MAX_VALUE;
    static ArrayList<CCTV> cctvs;

    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, 1, 0, -1};
    static class CCTV {
        int id;
        int x;
        int y;
        int rotationCount;
        int[] vector;
        public CCTV(int id, int x, int y) {
            super();
            this.id = id;
            this.x = x;
            this.y = y;
            if (id == 1) {
                rotationCount = 4;
                vector = new int[] {0};
            }
            if (id == 2) {
                rotationCount = 2;
                vector = new int[] {0, 2};
            }
            if (id == 3) {
                rotationCount = 4;
                vector = new int[] {0, 1};
            }
            if (id == 4) {
                rotationCount = 4;
                vector = new int[] {0, 1, 2};
            }
            if (id == 5) {
                rotationCount = 1;
                vector = new int[] {0, 1, 2, 3};
            }
        }

        public void monitor(int dx, int dy, int[][] map) {
            int nx = x;
            int ny = y;

            while (onMap(nx, ny) && map[nx][ny] != 6) {

                if (map[nx][ny] == 0) {
                    map[nx][ny] = -1;
                }
                nx += dx;
                ny += dy;
            }
        }

        @Override
        public String toString() {
            return "CCTV [id=" + id + ", x=" + x + ", y=" + y + ", rotationCount=" + rotationCount + "]";
        }
        // 1, 3, 4번은 4 방향 회전, 2번은 2 방향, 5번은 1방향 회전
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = nm[0];
        M = nm[1];
        int blindSpot = 0;
        visited = new boolean[N][M];
        int[][] map = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        dfs(0, map);
        System.out.println(MIN_BLIND_SPOT);
    }

    public static void dfs(int idx, int[][] map) {
        if (idx == cctvs.size()) {
            int blindSpot = countBlindSpot(map);
            MIN_BLIND_SPOT = Math.min(MIN_BLIND_SPOT, blindSpot);
            return;
        }
        // 현재  idx에 해당하는 cctv
        CCTV cctv = cctvs.get(idx);

        // 회전할 수 있는 각 방향마다 탐색한다.
        for (int vc = 0; vc < cctv.rotationCount; vc ++) {
            // 회전 시 마다 지도 원래 상태로 돌린다.
            int[][] copyMap = mapCopy(map); // copyMap에 복사하여 사용
            for (int v: cctv.vector) {
                int vector_idx = (v + vc) % 4;
                cctv.monitor(dx[vector_idx], dy[vector_idx], copyMap);
            }
            dfs(idx + 1, copyMap);
        }
    }
    public static boolean onMap(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < M);
    }

    public static int countBlindSpot(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) cnt ++;
            }
        }
        return cnt;
    }

    public static int[][] mapCopy(int[][] map) {
        int[][]newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
}