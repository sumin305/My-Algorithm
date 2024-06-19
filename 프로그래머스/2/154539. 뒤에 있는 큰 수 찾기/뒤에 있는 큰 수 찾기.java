import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Stack<int[]> stack = new Stack();
        stack.push(new int[] {numbers[0], 0});
        for (int i = 1; i < n; i ++) {
            while (!stack.isEmpty() && stack.peek()[0] < numbers[i]) {
                int[] target = stack.pop();
                answer[target[1]] = numbers[i];
            }
            stack.push(new int[] {numbers[i], i});
        }
        return answer;
    }
}