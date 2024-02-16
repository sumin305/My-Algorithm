import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int k;
	static int[][] line;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[] leftLine = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int[] rightLine = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		line = new int[2][N];
		visited = new boolean[2][N];
		line[0] = leftLine;
		line[1] = rightLine;
		visited[0][0] = true;
		System.out.println(BFS(0, 0));
	}
	
	public static int BFS(int isRight, int start) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {start, 0, 0});
		while (!queue.isEmpty()) {
			int[] target = queue.poll();
			int idx = target[0];
			int time = target[1];
			int right = target[2];
			
			// idx 가 이상이면 클리어
			if (idx >= N) return 1;

			// 앞으로 가거나 
			if (idx + 1 < N && (line[right][idx + 1] == 1 || idx + 1 == N) && !visited[right][idx + 1]) {
				visited[right][idx + 1] = true;
				queue.add(new int[] {idx + 1, time + 1, right});
			}
			// 뒤로 가거나 (사라지지 않은 칸인지 확인)
			if (idx - 1 >= 0 && idx - 1 > time && line[right][idx - 1] == 1 && !visited[right][idx - 1]) {
				visited[right][idx - 1] = true;
				queue.add(new int[] {idx - 1, time + 1, right});
			}
			// 옆으로 이동
			if (idx + k < N && line[1 - right][idx + k] == 1 && !visited[1 - right][idx + k]) {
				visited[1 - right][idx + k] = true;
				queue.add(new int[] {idx + k, time + 1, 1 - right});
			} else if (idx + k >= N) {
				return 1;
			}
		}
		return 0;
	}
}