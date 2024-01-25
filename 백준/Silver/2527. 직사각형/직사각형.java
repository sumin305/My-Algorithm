import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

class Rectangle {
    int x1;
    int y1;
    int x2;
    int y2;
    int width;
    int height;

    public Rectangle(int x1, int y1, int x2, int y2, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Rectangle r1 = null;
            Rectangle r2 = null;
            // 첫번째 직사각형
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            r1 = new Rectangle(x1, y1, x2, y2, x2 - x1, y2 - y1);

            // 두번째 직사각형
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = new Rectangle(x1, y1, x2, y2, x2 - x1, y2 - y1);

            // 겹치는 곳이 없는가?
            if (r1.x2 < r2.x1 || r1.y2 < r2.y1 || r1.x1 > r2.x2 || r1.y1 > r2.y2) {
                System.out.println("d");
            }
            // x나 y 좌표가 겹치는 경우
            else if (r1.y2 == r2.y1 || r1.x1 == r2.x2 || r1.y1 == r2.y2 || r1.x2 == r2.x1) {

                // r1 기준 오른쪽 위 | 왼쪽 아래 |
                if ((r1.y2 == r2.y1 && r1.x2 == r2.x1) || (r1.y1 == r2.y2) && (r1.x1 == r2.x2) || (r1.x1 == r2.x2) && (r1.y1 + r1.height == r2.y2 - r2.height) || (r1.y1 == r2.y2 && r1.x2 == r2.x2 - r2.width)) {
                    System.out.println("c");
                } else {
                    System.out.println("b");
                }
            }
            // 직사각형이 겹치는 경우
            else {
                System.out.println("a");
            }
        }
    }
}