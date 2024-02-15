import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static List<int[]> root;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int count;
    static boolean search;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        root = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            visited[i][0] = true;
            DFS(i, 0);
        }
        System.out.println(count);
    }

    public static boolean DFS(int x, int y) {
        map[x][y] = 'x';
        if (y == C - 1) {
            count++;
            return true;
        }
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx  >= R || map[nx][ny] == 'x') continue;
            if (DFS(nx, ny)) return true;
        }
        return false;
    }
}