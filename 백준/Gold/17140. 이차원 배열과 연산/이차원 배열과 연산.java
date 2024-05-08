import java.io.*;
import java.util.*;

public class Main {
	public static int rck[], arr[][], newArr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		rck = new int[3];
		arr = new int[3][3];
		rck = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int r = rck[0], c = rck[1], k = rck[2];
		for (int i = 0; i < 3; i++) arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int time = 0;
		while (true) {
			if (r - 1 < arr.length && c - 1 < arr[0].length && arr[r - 1][c - 1] == k) {
				System.out.println(time);
				break;
			}
			if (time == 100) {
				System.out.println(-1);
				break;
			}
			if (arr[0].length <= arr.length) {
				operationR(arr.length, arr[0].length);
			} else {
				operationC(arr.length, arr[0].length);
			}
			time++;
		}
	}
	private static void operationR(int rLen, int cLen) {
		if (cLen * 2 < 100)  newArr = new int[rLen][cLen * 2];
		int maxIdx = 0;
		for (int i = 0; i < rLen; i++) {
			HashMap<Integer, Integer> map = new HashMap();
			for (int j = 0; j < cLen; j++) {
				if (arr[i][j] == 0) continue;
				map.put(arr[i][j], map.get(arr[i][j]) == null ? 1 : map.get(arr[i][j]) + 1);
			}
			List<Integer> keySet = new ArrayList<>(map.keySet());
			keySet.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if (map.get(o1) == map.get(o2)) {
						return o1 - o2;
					}
					return map.get(o1) - map.get(o2);
				}
			});
			
			int idx = 0;
			for (Integer key: keySet) {
				newArr[i][idx++] = key;
				newArr[i][idx++] = map.get(key);
			}
			maxIdx = Math.max(maxIdx, idx);
		}
		
		arr = new int[rLen][maxIdx];
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < maxIdx; j++) {
				arr[i][j] = newArr[i][j];
			}
		}

	} 
	private static void operationC(int rLen, int cLen) {
		if (rLen * 2 < 100)  newArr = new int[rLen * 2][cLen];
		int maxIdx = 0;
		for (int i = 0; i < cLen; i++) {
			HashMap<Integer, Integer> map = new HashMap();
			for (int j = 0; j < rLen; j++) {
				if (arr[j][i] == 0) continue;
				map.put(arr[j][i], map.get(arr[j][i]) == null ? 1 : map.get(arr[j][i]) + 1);
			}

			List<Integer> keySet = new ArrayList<>(map.keySet());
			keySet.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if (map.get(o1) == map.get(o2)) {
						return o1 - o2;
					}
					return map.get(o1) - map.get(o2);
				}
			});
			
			int idx = 0;
			for (Integer key: keySet) {
				newArr[idx++][i] = key;
				newArr[idx++][i] = map.get(key);
			}
			maxIdx = Math.max(maxIdx, idx);
		}
		arr = new int[maxIdx][cLen];
		for (int i = 0; i < maxIdx; i++) {
			for (int j = 0; j < cLen; j++) {
				arr[i][j] = newArr[i][j];
			}
		}
	}
}
