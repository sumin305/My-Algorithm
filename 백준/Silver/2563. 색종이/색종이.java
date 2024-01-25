import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[100][100];
        int count = 0;
        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 10; i ++) {
                for (int j = 0; j < 10; j ++) {
                    if (paper[x + i][y + j] == 1) {
                        continue;
                    }
                    paper[x + i][y + j] = 1;
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}