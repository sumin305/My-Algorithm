import java.util.*;
import java.io.*;

class Solution {    
    int answer = 0;
    boolean[] visited;
    int n;
    int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        this.dungeons = dungeons;
        this.visited = new boolean[n];
        dfs(k, 0);
        return answer;
    }
    
                
    public void dfs (int stamina, int depth) {
        answer = Math.max(answer, depth);

        for (int i = 0; i < n; i++) {
            int[] dungeon = dungeons[i];
            if (!visited[i] && stamina >= dungeon[0]) {
                visited[i] = true;
                dfs(stamina - dungeon[1], depth + 1);
                visited[i] = false; 
            }
        }

    }

}