import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int maxLength = 0;
        int maxCount = 0;
        List<Integer>[] list = new ArrayList[n + 1];
        boolean[] visited = new boolean[n + 1];
        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList();
        }
        
        for (int[] e: edge) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {1, 0});
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            
            for (Integer adj: list[target[0]]) {
                if (visited[adj] == true) continue; 
                int length = target[1] + 1;
                if (length  > maxLength) {
                    maxLength = length;
                    maxCount = 1;
                } else if (length == maxLength) {
                    maxCount++;
                }
                queue.add(new int[] {adj, target[1] + 1});
                visited[adj] = true;
            }
        }
        return maxCount;
    }
}