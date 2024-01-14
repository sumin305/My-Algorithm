import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int sum = 0;
        for  (int i = 0; i < 9; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        // arr에서 두 개씩 뽑아서 sum에서 뺀 후, 100이면 출력한다.
        for (int i = 0; i < 9; i ++) {
            for (int j = i + 1; j < 9; j ++) {
                if (sum - arr[i] - arr[j] == 100) {
                    printExceptNum(arr, i, j);
                    return;
                }
            }
        }
    }
    public static void printExceptNum(int[] arr, int i, int j) {
        for (int k = 0; k < 9; k ++) {
            if (k == i || k == j) continue;
            System.out.println(arr[k]);
        }
    }
}