import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException  {
		System.out.println(solution());
	}
	
	public static long solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Long> nums = new ArrayList();
		if (N > 1022) return -1;
		if (N <= 10) return N;
		

		for (int i = 0; i <= 9; i++) {
			List<Long> temp = new ArrayList();
			for (Long num: nums) {
				temp.add(Long.parseLong(String.valueOf(i) + String.valueOf(num)));
			}
			for (Long t: temp) nums.add(t);
			nums.add((long)i);
		}
		Collections.sort(nums);
		return nums.get(N);
	}
}