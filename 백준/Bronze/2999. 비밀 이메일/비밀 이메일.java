import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();
        int row = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                if (i <= N / i) {
                    row = i;
                }
            }
        }

        int col = N / row;
        Character[][] table = new Character[row][col];
        int idx = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                table[j][i] = str.charAt(idx++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result.append(Character.toString(table[i][j]));
            }
        }
        System.out.println(result);
    }
}