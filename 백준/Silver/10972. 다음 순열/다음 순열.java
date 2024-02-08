import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		int[] target = new int[N];
		for (int i = 0; i < N; i++) {
			target[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		int[] newArray = target.clone();
		nextpermutation(target);
		if (Arrays.equals(newArray, target)) System.out.println(-1);
		else {
			for (int t: target) {
				System.out.print(t + " ");
			}
		}
	}
	
	public static boolean nextpermutation(int[] p) {
		final int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i-1] >= p[i]) --i;
		
		if (i == 0) return false;
		
		int j = N - 1;
		while (p[i - 1] >= p[j]) --j;
		
		swap(p, i-1, j);
		
		int k = N - 1;
		while (i < k) swap(p, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}