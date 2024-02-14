import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine());
		int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(A);
		
		for (int b: B) {
			int target = searchNum(0, A.length - 1, b);
			System.out.println(target == -1 ? 0 : 1);
		}
	}
	
	public static int searchNum(int start, int end, int num) {
		if (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == num) return mid;
			if (A[mid] > num) return searchNum(start, mid - 1, num);
			return searchNum(mid + 1, end, num);
		}
		return -1;
	}
}