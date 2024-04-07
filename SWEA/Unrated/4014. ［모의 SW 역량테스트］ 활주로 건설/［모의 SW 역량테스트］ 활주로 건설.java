import java.io.*;
import java.util.*;

public class Solution {
	public static int N, X, map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int result = 0;
			for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < N; i++) {
				if (check(map[i])) {
					result++;
				}
				int[] temp = new int[N];
				for (int j = 0; j < N; j++) {
					temp[j] = map[j][i];
				}
				if (check(temp)) {
					result++;
				}
			}
			System.out.println("#" + t + " " + result);
		}
		
	}
	
	public static boolean check(int[] line) {
		boolean complete = true; // 길이를 맞췄으면 true
		int temp = line[0];
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (line[i] == temp) {
				cnt++;
				if (!complete & cnt > X) {
					complete = true;
					cnt = 1;
				}
			} else {
				if (line[i] == temp + 1) { // 한칸 높이가 증가했을때 
					if (!complete || cnt < X) return false;
					complete = true;
					cnt = 1;
					temp = line[i];
				} else if (line[i] == temp - 1) { // 한칸 높이가 감소했을때
					if (!complete) { // 이전에 증가하다가 한칸 감소한 경우, 그 count 개수는 고려하지 않아도 된다. 
						if (cnt < X) {
							return false;
						}
					}
					complete = false;
					cnt = 1;
					temp = line[i];
				} else {
					return false;
				}
			}
		}
		
		// 마지막에 경사로의 길이에 비해 지형이 짧을 경우 
		if (!complete && cnt < X) {
			return false;
		}
		return true;
	}
}