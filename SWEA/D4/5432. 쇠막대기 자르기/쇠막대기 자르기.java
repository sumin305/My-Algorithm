import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			char[] target = br.readLine().toCharArray();
			Stack<Integer> stack = new Stack<>(); // 쇠막대기들의 레이저를 맞은 수를 담는다.
			int count = 0;
			for (char c: target) {
				if (c == '(') {
					stack.push(0);
				}
				
				else if (c == ')') {
					if (!stack.isEmpty()) {
						int t = stack.pop();
						
						// 레이저일때
						if (t == 0) {
							if (!stack.isEmpty()) {
								stack.push(stack.pop() + 1);
							}
						} 
						
						// 막대기의 끝일때 
						else {
							count += (t + 1); // 해당 쇠막대기의 끝을 만났으므로 그동안 잘려진 쇠막대기 개수를 더한다.
							if (!stack.isEmpty()) {
								stack.push(stack.pop() + t);
							}
						}
					}
				}
			}
			System.out.println("#" + test_case + " " + count);
		}
	}
}