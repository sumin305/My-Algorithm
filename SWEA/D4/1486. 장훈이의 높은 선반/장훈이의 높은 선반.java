import java.io.*;
import java.util.*;

public class Solution {
	public static int N, B, minDiff, arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			minDiff = Integer.MAX_VALUE;
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
			selectMember(0, 0);
			System.out.println("#" + t + " " + minDiff);
		}
	}
	
	public static void selectMember(int i, int sum) {
		if (i == N) {
			if (sum >= B) {
				minDiff = Math.min(minDiff, sum - B);
			}
			return;
		}
		selectMember(i + 1, sum + arr[i]);
		selectMember(i + 1, sum);
	}
}