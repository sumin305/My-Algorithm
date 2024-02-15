import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int M, A, T, sum;
	static int[] aMove, bMove;
	static BC[] bcs;
	static int[] dx = new int[] {0, -1, 0, 1, 0};
	static int[] dy = new int[] {0, 0, 1, 0, -1};
	
	static boolean[] visited;
	static BC[] selected = new BC[2];
	static int maxP, n;
	static class BC {
		int x;
		int y;
		int range;
		int P;
		public BC(int x, int y, int range, int p) {
			super();
			this.x = x;
			this.y = y;
			this.range = range;
			P = p;
		}
		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", range=" + range + ", P=" + P + "]";
		}
		public boolean equals(BC obj) {
			// TODO Auto-generated method stub
			return (this.x == obj.x && this.y == obj.y);
		}		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			input();
			sum = 0;
			move(0, 0, 0, 9, 9);
			System.out.println("#" + test_case + " " + sum);
		}
	}
	

	private static void move(int time, int Ax, int Ay, int Bx, int By) {
		// TODO Auto-generated method stub

		// A가 위치한 곳이 충전 범위인 BC
		List<BC> AinRangeBC = checkChargingRange(Ax, Ay);
		// B가 위치한 곳이 충전 범위인 BC
		List<BC> BinRangeBC = checkChargingRange(Bx, By);
		
		charge(AinRangeBC, BinRangeBC);
		
		if (time == M) {
			return;
		}

		move(time + 1, Ax + dx[aMove[time]], Ay + dy[aMove[time]], Bx + dx[bMove[time]], By + dy[bMove[time]]);
	}
	
	private static void charge(List<BC> AinRangeBC, List<BC> BinRangeBC) {
		if (AinRangeBC.size() == 0 && BinRangeBC.size() == 0) return;
		if (AinRangeBC.size() == 0 || BinRangeBC.size() == 0) {
			AinRangeBC.addAll(BinRangeBC);
			AinRangeBC.sort((o1, o2) -> o2.P - o1.P);
			sum += AinRangeBC.get(0).P;
			return;
		} else {
			maxP = 0;
			selectBC(AinRangeBC, BinRangeBC);
			sum += maxP;
		}
	}

	
	public static void selectBC(List<BC> AinRangeBC, List<BC> BinRangeBC) {
		for (BC bc1: AinRangeBC) {
			for (BC bc2: BinRangeBC) {
				int sumP;
				if (bc1.equals(bc2)) sumP = bc1.P;
				else sumP = bc1.P + bc2.P;
				maxP = Math.max(maxP, sumP);
			}
		}
	}

	// 해당 좌표에서 충전할 수 있는 BC list 반환
	public static List<BC> checkChargingRange(int x, int y) {
		List<BC> inRangeBC = new ArrayList<BC>();
		for (int i = 0; i < A; i++) {
			if (Math.abs(bcs[i].x - x) + Math.abs(bcs[i].y - y) <= bcs[i].range) {
				inRangeBC.add(bcs[i]);
			}
		}
		return inRangeBC;
	}

	// 입력받기
	public static void input() throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		aMove = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		bMove = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		bcs = new BC[A];
		for (int i = 0; i < A; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
			BC bc = new BC(temp[1] - 1, temp[0] - 1, temp[2], temp[3]);
			bcs[i] = bc;
		}
	}
}
