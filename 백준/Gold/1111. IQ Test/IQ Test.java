import java.io.*;
import java.util.*;

public class Main {
	public static int N, arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(solution());		
	}
	
	public static String solution() {
		if (N == 2 && arr[0] == arr[1]) return String.valueOf(arr[0]);
		if (N <= 2) return "A";
		
		int num1 = arr[0];
		int num2 = arr[1];
		int num3 = arr[2];
		
		if (num1 == num2) {
			if (Arrays.stream(arr).distinct().count() == 1) {
				return String.valueOf(num1);
			} else {
				return "B";
			}
		}

		double a = (double)(num2 - num3) / (double)(num1 - num2);
		if (a % 1 != 0) return "B";
		
		double b = (num2 - a * num1);
		if (b % 1 != 0) return "B";
		for (int i = 2; i < N - 1; i++) {
			if (a * arr[i] + b != arr[i + 1]) return "B";
		}
		
		return String.valueOf((int)(arr[N - 1] * a + b));
	}
}