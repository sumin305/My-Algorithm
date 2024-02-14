import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[][] maps;
	static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		long sq = (int)Math.pow(2, N);
		makeV(sq, r, c);
		System.out.println(count - 1);
		sc.close();
	}
	
	public static void makeV(long sq, long x, long y) {
		long half = sq / 2;
		if (half == 1) {
			if (0 <= x && x < half && 0 <= y && y < half) { // 1사분면
				count += 1;
			} else if (0 <= x && x < half && y >= half) { // 2사분면
				count += 2;
			} else if (x >= half && 0 <= y && y < half) {
				count += 3;
			} else {
				count += 4;
			}
			return;
		}
		if (0 <= x && x < half && 0 <= y && y < half) { // 1사분면
			makeV(sq / 2, x, y);
		} else if (0 <= x && x < half && y >= half) { // 2사분면
			count += (half * half);
			makeV(sq / 2, x, y - half);
		} else if (x >= half && 0 <= y && y < half) {
			count += 2 * (half * half);
			makeV(sq / 2, x - half, y);
		} else {
			count += 3 * (half * half);
			makeV(sq / 2, x - half, y - half);
		}
	}
}