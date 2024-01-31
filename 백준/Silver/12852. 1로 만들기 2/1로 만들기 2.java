import java.util.Scanner;
public class Main {

	static int n;
	static int min_count = Integer.MAX_VALUE;
	static String result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		makeOne(n, 0, String.valueOf(n) + " ");
		System.out.println(min_count);
		System.out.println(result);

	}

	public static void makeOne(int num, int count, String str) {
		if (num == 1) {
			if (count < min_count) {
				min_count = count;
				result = str;
			}
			return;
		}
		if (count + 1 > min_count) return;

		if (num % 3 == 0) {
			makeOne(num / 3, count + 1, str + num / 3 + " ");
		}
		if (num % 2 == 0) { 
			makeOne(num / 2, count + 1, str + num / 2 + " ");
		}
		makeOne(num - 1, count + 1, str + (num - 1) + " ");
	}
}