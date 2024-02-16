import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int A[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int a = A[0], b = A[1];
		
		int m = Integer.parseInt(br.readLine());
		int[] parents = new int[n + 1];
			
		for (int i = 0; i < m; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			parents[temp[1]] = temp[0];
		}

		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		listA.add(a);
		listB.add(b);
		while(true) {
			if(parents[a]==0)break;
			listA.add(parents[a]);
			a=parents[a];
		}
		while(true) {
			if(parents[b]==0)break;
			listB.add(parents[b]);
			b=parents[b];
		}
		
		int length = Integer.MAX_VALUE;
		for(int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i) == listB.get(j)) {
					length = Math.min(length, i + j);
				}
			}
		}
		
		if (length == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(length);
	}

}
