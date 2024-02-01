import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] groupA;
	static int[] groupB;
	static int N;
	static boolean[] visited;
	static boolean[] tempVisited;
	static int [][]  ingredientsMap;
	static int minSynergyDiff;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			minSynergyDiff = Integer.MAX_VALUE;
			ingredientsMap = new int[N][N];
			groupA = new int [N/2];
			groupB = new int [N/2];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ingredientsMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			makeGroup(0, 0);
			System.out.println("#" + test_case + " " + minSynergyDiff);
		}
	}
	// N개에서 N/2를 뽑는다 (조합)
	public static void makeGroup(int idx, int start) {
		if (idx == N/2) {
			int idxA = 0, idxB = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					groupA[idxA++] = i;
				} else {
					groupB[idxB++] = i;
				}
			}
			synergy = 0;
			tempVisited = new boolean[N / 2];
			calculateSynergy(groupA, 0, 0);
			int synergyA = synergy;
			synergy = 0;
			tempVisited = new boolean[N / 2];
			calculateSynergy(groupB, 0, 0);
			int synergyB = synergy;

			minSynergyDiff = Math.min(minSynergyDiff, Math.abs(synergyA - synergyB));
			return;
		}
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				makeGroup(idx + 1, i + 1);
				visited[i] = false;
			}
		}
	}
	static int synergy;
	static int[] temp = new int[2];

	public static void calculateSynergy(int[] array, int idx, int start) {
		if (idx == 2) {
			synergy += ingredientsMap[temp[0]][temp[1]];
			synergy += ingredientsMap[temp[1]][temp[0]];
			return;
		}
		for (int i = start; i < array.length; i++) {
			if (!tempVisited[i]) {
				tempVisited[i] = true;
				temp[idx] = array[i];
				calculateSynergy(array, idx + 1, i + 1);
				tempVisited[i] = false;
			}
		}
	}
}
