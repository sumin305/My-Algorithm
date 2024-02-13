import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 1000 - sc.nextInt();
		int count = 0;
		int[] money = {500, 100, 50, 10, 5, 1};
		
		for (int m: money) {
			if (N == 0) break;
			count += (N / m);
			N %= m;
		}
		System.out.println(count);
	}
}