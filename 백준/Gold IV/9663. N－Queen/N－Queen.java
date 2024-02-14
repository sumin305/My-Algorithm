import java.util.Scanner;
import java.util.Arrays;
public class Main {
    static int N;
    static int[] arr;
    static int queenCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        makeQueen(0);
        System.out.println(queenCount);
    }

    public static void makeQueen(int idx) {
        if (idx == N) {
            queenCount++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[idx] = i;
            if (check(idx)) {
                makeQueen(idx + 1);
            }
        }
    }

    public static boolean check(int idx) {
        for (int i = 0; i < idx; i++) {
            if (arr[i] == arr[idx]) return false;
            if (Math.abs(i - idx) == Math.abs(arr[i] - arr[idx])) return false;
        }
        return true;
    }
}