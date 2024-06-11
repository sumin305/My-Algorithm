import java.util.*;
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList();
        int n = progresses.length;
        boolean[] visited = new boolean[n];
        int[] deploymentTime = new int[n];
        int time = 0;
        while (!allVisited(visited)) {
            time++;
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                progresses[i] += speeds[i];
                if (progresses[i] >= 100) {
                    visited[i] = true;
                    deploymentTime[i] = time;
                }
            }
        }
        System.out.println(Arrays.toString(deploymentTime));
        Stack<Integer> deploymentStack = new Stack();
        
        for (int i = n - 1; i >= 0; i--) {
            deploymentStack.push(deploymentTime[i]);
        }
        
        int cnt = 0;
        int temp = deploymentTime[0];
        while (!deploymentStack.isEmpty()) {
            int target = deploymentStack.pop();
            System.out.println(target + " " + temp);
            if (temp >= target) {
                cnt ++;
            } else {
                result.add(cnt);
                cnt = 1;
            }
            temp = Math.max(temp, target);
        }
        if (cnt > 0) result.add(cnt);
        return result;
    }
    
    public boolean allVisited(boolean[] visited) {
        for (boolean v: visited) {
            if (!v) return false;
        }
        return true;
    }
}