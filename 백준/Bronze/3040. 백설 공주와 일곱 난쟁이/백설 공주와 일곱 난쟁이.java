import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dwarfs = new int[9];
	static boolean[] visited = new boolean[9];
	static int[] reals = new int[7];
	static boolean search;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
		}
		makeNums(0, 0, 0);
	}
	
	public static void makeNums(int idx, int start, int total) {
		if (search) return;
		if (idx == reals.length) {
			if (total == 100) {
				for (int t: reals) {
					System.out.println(t);
				}
				search = true;
			}
			return;
		}
		
		for (int i = start; i <= dwarfs.length - 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				reals[idx] = dwarfs[i];
				makeNums(idx + 1, i + 1, total + dwarfs[i]);
				if (search) return;
				visited[i] = false;
			}
		}
	}

}