import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int result = Integer.MAX_VALUE;
	static int[] peoples;
	static boolean[] visited;
	static boolean[] selected;
	static ArrayList<Integer> graph[];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		peoples = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		graph = new ArrayList[N + 1];
		selected = new boolean[N];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int m = temp[0];
			for (int j = 1; j <= m; j++) {
				graph[i].add(temp[j]);
			}
		}
		divide(1);
		if (result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}
	
	public static void divide(int idx) {
		if (idx == N) {
			List<Integer> a = new ArrayList<>();
			List<Integer> b = new ArrayList<>();
			 
			for (int i = 1; i <= N; i++) {
				if (selected[i - 1]) a.add(i);
				else b.add(i);
			}
			if (a.size() == 0 || b.size() == 0) return; //  구역을 적어도 하나 포함해야 한다
			// 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다
			if (connectCheck(a) && connectCheck(b)) { 
				// 인구수 차이 계산
				int diff = calculateDifference();
				result = Math.min(result, diff);
			}
			return;
		}
		
		selected[idx] = true;
		divide(idx + 1);
		selected[idx] = false;
		divide(idx + 1);
	}
	
	public static boolean connectCheck(List<Integer> list) {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N + 1];
		visited[list.get(0)] = true;

		q.add(list.get(0));
		int count = 1;
		while(!q.isEmpty()) {
			int target = q.poll();
			for (int adj: graph[target]) {
				if (!visited[adj] && list.contains(adj)) {
					visited[adj] = true;
					q.add(adj);
					count ++;
				}
			}
		}
		if (count == list.size()) return true;
		else return false;
	}
	public static int calculateDifference() {
		int populationA = 0;
		int populationB = 0;
		
		for (int i = 1; i <= N; i++) {
			if (visited[i]) populationA += peoples[i - 1];
			else populationB += peoples[i - 1];
		}
		
		return Math.abs(populationA - populationB);
	}
}