import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static int ops[], selectedOps[], arr[], maxValue, minValue, N;
    public static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] op = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ops = new int[N - 1];
        selected = new boolean[ops.length];
        selectedOps = new int[N - 1];
        maxValue = -1000000000;
        minValue = 1000000000;
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < op[i]; j++) {
                ops[idx++] = i;
            }
        }

        per(0);
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    public static void per(int i) {
        if (i == N - 1) {
            // 연산값
            int result = calculate();
            maxValue = Math.max(maxValue, result);
            minValue = Math.min(minValue, result);
            return;
        }

        for (int j = 0; j < N - 1; j++) {
            if (!selected[j]) {
                selected[j] = true;
                selectedOps[i] = ops[j];
                per(i + 1);
                selected[j] = false;
            }
        }
    }

    public static int calculate() {
        int result = arr[0];
        for (int i = 0; i < N - 1; i++) {
            switch (selectedOps[i]) {
                case 0:
                    result += arr[i + 1];
                    break;
                case 1:
                    result -= arr[i + 1];
                    break;
                case 2:
                    result *= arr[i + 1];
                    break;
                case 3:
                    result /= arr[i + 1];
                    break;
            }
        }
        return result;
    }
}
