import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        String[][] baseball = new String[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                baseball[i][j] = st.nextToken();
            }
        }
        for (int i = 123; i <= 987; i++) {
            String target = String.valueOf(i);
            if (target.contains("0")) continue;
            if (target.charAt(0) == target.charAt(1) || target.charAt(0) == target.charAt(2) || target.charAt(1) == target.charAt(2)) continue;
            for (int j = 0; j < N; j++) {
                boolean possible = false;
                // 같은 수, 같은 자리인 숫자의 수가 baseball[j][1]과 같고
                // 같은 수, 다른 자리인 숫자의 수가 baseball[j][2]과 같아야함


                int strikeCount = 0;
                int ballCount = 0;

                for (int k = 0; k < 3; k++) {
                    String temp = baseball[j][0];
                    if (temp.charAt(k) == target.charAt(k)) {
                        strikeCount++;
                    }
                    for (int h = 0; h < 3; h++) {
                        if (k == h) continue;
                        if (temp.charAt(k) == target.charAt(h)) {
                            ballCount++;
                        }
                    }
                }
                if (strikeCount == Integer.parseInt(baseball[j][1]) && ballCount == Integer.parseInt(baseball[j][2])) {
                    possible = true;
                }
                if (!possible) {
                    break;
                }
                if (j == N - 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
