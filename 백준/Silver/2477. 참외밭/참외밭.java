import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int melonsCount = Integer.parseInt(br.readLine());
        String[] strArr = new String[6];
        int maxH = 0, maxV = 0;
        int[] arr = new int[6];
        int maxArea = 1;
        // x, y의 각 최댓값 찾고, arr에 넣는다.
        for (int i = 0; i < 6; i ++) {
            strArr[i] = br.readLine();
            StringTokenizer st = new StringTokenizer(strArr[i]);
            int v = Integer.parseInt(st.nextToken()), num = Integer.parseInt(st.nextToken());
            if (v == 1 || v == 2) maxH = Math.max(maxH, num);
            else maxV = Math.max(maxV, num);
            arr[i] = num;
        }
        maxArea = (maxH * maxV);

        // maxH, maxV 두 개가 연달아 나온 후에, 다다음, 다다다음 변의 곱이 작은 직사각형의 넓이이다.
        int minArea = 1;
        int idx = 0;
        int flag = 0;
        while (idx < arr.length) {
            if (arr[idx] == maxH || arr[idx] == maxV) {
                flag ++;
            } else {
                flag = 0;
            }
            if (flag == 2) {
                minArea = minArea *  (arr[(idx + 2) % 6]) * (arr[(idx + 3) % 6]);
                break;
            }
            idx ++;
            if (idx == arr.length) idx = 0;
        }

        int area = maxArea - minArea;
        System.out.println(area * melonsCount);
    }
}