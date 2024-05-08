
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int sIdx = 0;
		int eIdx = N - 1;
		int minDiff = Integer.MAX_VALUE;
		int[] result = new int[2];
		while (sIdx < eIdx) {
			int sum = arr[eIdx] + arr[sIdx];
			if (Math.abs(sum) < minDiff) {
				result[0] = arr[sIdx];
				result[1] = arr[eIdx];
				minDiff = Math.abs(sum);
			}
			if (sum < 0) {
				sIdx++;
			} else {
				eIdx--;
			}
		}
		System.out.println(result[0] + " " + result[1]);
	}

}
