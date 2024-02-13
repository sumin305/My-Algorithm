import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][2];
		for (int i = 0; i < N; i++) {
			meetings[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		Arrays.sort(meetings, (o1, o2) ->  {
			if (o1[1] == o2[1]) return o1[0] - o2[0];
			return o1[1] - o2[1];
		});
		int count = 1;
		int currentFinishTime = meetings[0][1];
		
		for (int i = 1; i < N; i++) {
			if (currentFinishTime <= meetings[i][0]) {
				currentFinishTime = meetings[i][1];
				count++;
			}
		}
		System.out.println(count);
	}
}