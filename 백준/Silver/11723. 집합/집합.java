import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int target = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int num = 0;
			if (st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());
			switch (op) {
			case "add":
				target |= (1 << (num - 1));
				break;
			case "remove":
				target &= ~(1 << (num - 1));
				break;
			case "check":
				sb.append((target & (1 << (num - 1))) == (1 << (num - 1)) ? "1" : "0").append("\n");
				break;
			case "toggle":
				target ^= (1 << (num - 1));
				break;
			case "all":
				target = (1 << 21) - 1;
				break;
			case "empty":
				target = 0;
				break;
			}
		}
		System.out.println(sb.toString());
	}
}