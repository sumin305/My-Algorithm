import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] eggs;
    static int maxBrokenEggsCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            eggs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        breakEgg(0, eggs);
        System.out.println(maxBrokenEggsCount);
    }

    public static void breakEgg(int hand, int[][] eggs) {
        if (hand == N) {
            int count = 0;
            for (int[] egg: eggs) {
                if (egg[0] <= 0) count++;
            }
            maxBrokenEggsCount = Math.max(maxBrokenEggsCount, count);
            return;
        }
        if (eggs[hand][0] <= 0) {
            breakEgg(hand + 1, eggs);
            return;
        }

        boolean lastEggCanBreak = false;
        for (int i = 0; i < N; i++) {
            if (eggs[i][0] > 0 && hand != i) { // 깨지지 않은 달걀만 / 들고있는 달걀 제외
                if (i == N - 1) lastEggCanBreak = true;
                eggs[i][0] -= eggs[hand][1];
                eggs[hand][0] -= eggs[i][1];
                breakEgg(hand + 1, eggs);
                eggs[i][0] += eggs[hand][1];
                eggs[hand][0] += eggs[i][1];
            }
        }
        if (!lastEggCanBreak) breakEgg(hand + 1, eggs);
    }
}