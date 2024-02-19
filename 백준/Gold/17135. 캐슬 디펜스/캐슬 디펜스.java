import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] map;
    static int[] selectedArchersIndex; // 선택된 궁사 index
    static boolean[] visited;
    static int MAX_ENEMY_COUNT = 0;
    final static int ARCHER_COUNT = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = temp[0];
        M = temp[1];
        D = temp[2];
        map = new int[N][M];
        visited = new boolean[M];
        selectedArchersIndex = new int[ARCHER_COUNT];
        for (int i = 0; i < N; i ++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        selectArchersIndex(0, 0);
        System.out.println(MAX_ENEMY_COUNT);
    }

    public static void selectArchersIndex(int idx, int start) {
        if (idx == ARCHER_COUNT) {
            int removeEnemyCount = attackEnemy();
            // 해당 인덱스의 궁사들로 제거할 수 있는 적의 수 구하고 최대값 갱신
            MAX_ENEMY_COUNT = Math.max(MAX_ENEMY_COUNT, removeEnemyCount);
            return;
        }

        for (int i = start; i < M; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedArchersIndex[idx] = i;
                selectArchersIndex(idx + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static int attackEnemy() {
        // 맵 복사
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        int attackEnemyCount = 0;
        int[] dx = {0, -1, 0};
        int[] dy = {-1, 0, 1};

        while (existEnemy(newMap)) {
            // 궁사가 공격
            List<int[]> attackedEnemies = new ArrayList<>();
            for (int selectedArcherIdx: selectedArchersIndex) {
                Queue<int[]> queue = new ArrayDeque<>();
                boolean[][] visited = new boolean[N][M];
                queue.add(new int[] {N-1, selectedArcherIdx, 1, selectedArcherIdx});
                while (!queue.isEmpty()) {
                    int[] target = queue.poll();
                    int x = target[0];
                    int y = target[1];
                    int dis = target[2];
                    int idx = target[3];

                    if (newMap[x][y] == 1) {
                        attackedEnemies.add(new int[]{x, y});
                        break;
                    }

                    for (int v = 0; v < dx.length; v++) {
                        int nx = x + dx[v];
                        int ny = y + dy[v];

                        if (onMap(nx, ny) && !visited[nx][ny] && dis + 1 <= D) { // 방문하지 않았고, 거리가 D 이하인 경우
                            visited[nx][ny] = true;
                            queue.add(new int[] {nx, ny, dis + 1, idx});
                        }
                    }
                }
            }

            // 적 제거
            for (int[] coor: attackedEnemies) {
                if (newMap[coor[0]][coor[1]] == 1) {
                    attackEnemyCount ++;
                    newMap[coor[0]][coor[1]] = 0;
                }
            }

            // 한칸씩 내려옴
            int[] before = new int[M];
            for (int i = 0; i < N - 1; i++) {
                int[] temp = newMap[i];
                newMap[i] = before;
                before = temp;
            }
            newMap[N - 1] = before;
        }
        return attackEnemyCount;
    }
    public static boolean onMap(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean existEnemy(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
 }