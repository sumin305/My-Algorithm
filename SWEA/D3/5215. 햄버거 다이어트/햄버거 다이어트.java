import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int maxPreference = 0;
	static int N;
	static int L;
	static int[][] ingredients;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ingredients = new int[N][2]; // 맛 점수와 칼로리를 담는다.
            maxPreference = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredients[i][0] = Integer.parseInt(st.nextToken());
				ingredients[i][1] = Integer.parseInt(st.nextToken());
			}
			subSet();
			System.out.println("#" + test_case + " " + maxPreference);
		}
	}
	
	// 각 조합의 부분집합을 모두 구한다.
	public static void subSet() {
		for (int i = 0; i < (1 << N); i++) {
			int preferenceSum = 0;
			int	calorieSum = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					preferenceSum += ingredients[j][0];
					calorieSum += ingredients[j][1];
				}
			}
			if (calorieSum <= L) {
				maxPreference = Integer.max(maxPreference, preferenceSum);
			}
		}
	}
}