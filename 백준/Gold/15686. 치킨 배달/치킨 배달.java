import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int[][] map;
	public static int[][] selectedChickens;
	public static int[][] chickens;
	public static boolean[] visited;
	public static int[][] homes;
	public static int N;
	public static int M;
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int chickenCount = 0; 
		int homeCount = 0;
		 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if (temp == 2) chickenCount++;
				if (temp == 1) homeCount++;
			}
		}
		
		visited = new boolean[chickenCount];
		selectedChickens = new int[M][2];
		chickens = new int[chickenCount][2];
		homes = new int[homeCount][2];
		
		int chickenIndex = 0, homeIndex = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					homes[homeIndex][0] = i;
					homes[homeIndex++][1] = j;
				}
				if (map[i][j] == 2) {
					chickens[chickenIndex][0] = i;
					chickens[chickenIndex++][1] = j;
				}
			}
		}
		selectChicken(0, 0);
		System.out.println(result);
	}
	
	public static void selectChicken(int idx, int start) {
		if (idx == M) {
			// 해당 치킨집들만 남았을 때의 도시 치킨 거리 구하기
			int sum = getCityChickenDistance();
			result = Math.min(result,  sum);
			return;
		}
		for (int i = start; i < chickens.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selectedChickens[idx] = chickens[i];
				selectChicken(idx + 1, i + 1);
				visited[i] = false;
			}
		}
	}
	
	public static int getCityChickenDistance() {
		int sum = 0;
		for (int[] home: homes) {
			int minDistance = Integer.MAX_VALUE;
			for (int[] chicken: selectedChickens) {
				minDistance = Math.min(minDistance, Math.abs(chicken[0] - home[0]) + Math.abs(chicken[1] - home[1]));
			}
			sum += minDistance;
		}
		return sum;
	}
}