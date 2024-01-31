import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int H;
    static int W;

    public static void main(String[] args) throws NumberFormatException, IOException {
        char[][] map;
        int tank_vector = 0; // 위 아래 왼쪽 아래쪽
        int tank_x = 0;
        int tank_y = 0;
        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};
        final char[] charVector = {'^', 'v', '<', '>'};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    char target = map[i][j];
                    if (target == '^' || target == 'v' || target == '<' || target == '>') {
                        tank_x = i;
                        tank_y = j;
                    }
                }
            }

            // 탱크의 방향 설정하기
            switch(map[tank_x][tank_y]) {
                case '^':
                    tank_vector = 0;
                    break;
                case 'v':
                    tank_vector = 1;
                    break;
                case '<':
                    tank_vector = 2;
                    break;
                case '>':
                    tank_vector = 3;
                    break;
            }

            int n = Integer.parseInt(br.readLine());
            char[] input = br.readLine().toCharArray();
            for (char c: input) {
                switch(c) {
                    case 'U':
                        tank_vector = 0;
                        break;
                    case 'D':
                        tank_vector = 1;
                        break;
                    case 'L':
                        tank_vector = 2;
                        break;
                    case 'R':
                        tank_vector = 3;
                        break;
                    case 'S':
                        int nx = tank_x + dx[tank_vector];
                        int ny = tank_y + dy[tank_vector];

                        while (true) {

                            // 맵 밖을 나가거나 강철로 만들어진 벽을 만난 경우
                            if (!onMap(nx, ny) || map[nx][ny] == '#') break;

                            // 벽돌로 만들어진 벽을 만난 경우
                            if (map[nx][ny] == '*') {
                                map[nx][ny] = '.';
                                break;
                            }
                            nx += dx[tank_vector];
                            ny += dy[tank_vector];
                        }
                        break;
                }
                if (c != 'S') {
                    int nx = tank_x + dx[tank_vector];
                    int ny = tank_y + dy[tank_vector];
                    if (onMap(tank_x, tank_y))
                        map[tank_x][tank_y] = charVector[tank_vector];
                    if (onMap(nx, ny) && map[nx][ny] == '.') {
                        map[tank_x][tank_y] = '.';
                        tank_x = nx;
                        tank_y = ny;
                        map[tank_x][tank_y] = charVector[tank_vector];
                    }
                }
            }
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static boolean onMap(int x, int y) {
        if (0 <= x && x < H && 0 <= y && y < W) {
            return true;
        }
        return false;
    }
}