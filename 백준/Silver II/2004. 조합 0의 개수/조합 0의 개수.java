import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int twoNum = fact(n, 2) - (fact(m, 2) + fact(n-m, 2));
		int fiveNum = fact(n, 5) - (fact(m, 5) + fact(n-m, 5));
		int result = Math.min(twoNum, fiveNum);
		System.out.println(result);

	}
	
	private static int fact(int target, int num) {
		int cnt = 0;
		while (target >= num) {
			cnt += (target / num);
			target /= num;
		}
		return cnt;
	}
}