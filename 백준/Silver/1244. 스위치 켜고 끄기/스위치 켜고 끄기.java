import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[N];

        // 스위치 배열 입력받기
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        // 학생별로 스위치 처리하기
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i ++) {
            String[] tmp = br.readLine().split(" ");
            int sex = Integer.parseInt(tmp[0]), num = Integer.parseInt(tmp[1]);
            if (sex == 1) {
                arr = switchByMan(arr, num);
            } else {
                arr = switchByWoman(arr, num);
            }
        }
        for (int i = 0; i < N; i ++) {
            System.out.print(arr[i] + " ");
            if (((i + 1) % 20) == 0) {
                System.out.println();
            }
        }
    }
    public static int[] switchByMan(int[] arr, int target) {
        for (int i = 1; i * target - 1< arr.length; i ++) {
            arr[i * target - 1] = Math.abs(1 - arr[i * target - 1]);
        }
        return arr;
    }

    public static int[] switchByWoman(int[] arr, int target) {
        int left = target - 1, right = target - 1;
        arr[target - 1] = Math.abs(1 - arr[target - 1]);
        while (left >= 0 && right < arr.length) {
            if (arr[left] != arr[right])  break;
            arr[left] = Math.abs(1 - arr[left --]);
            arr[right] = Math.abs(1 - arr[right ++]);
        }
        return arr;
    }
}