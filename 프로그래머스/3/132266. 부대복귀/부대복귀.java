import java.io.*;
import java.util.*;

class Solution {
    boolean[] visited;
    List<Integer>[] nodes;
    int[] result;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        result = new int[sources.length];
        visited = new boolean[n + 1];
        nodes = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList();
        }
        
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];
            nodes[a].add(b);
            nodes[b].add(a);
        }
       
        // destination에서부터 BFS를 하며, 각 node까지의 거리를 구해라
        result = bfs(destination, n, sources);
        
        
        return result;
    }
    
    public int[] bfs(int start, int n, int[] sources) {
        Queue<int[]> q = new ArrayDeque();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        q.add(new int[] {start, 0});
        
        while (!q.isEmpty()) {
            int[] target = q.poll();
            
            int node = target[0];
            int dis = target[1];
            
            visited[node] = true;
            distance[node] = dis;
            
            for (Integer adj: nodes[node]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.add(new int[] {adj, dis + 1});
                }
            }
        }
        
        return Arrays.stream(sources).map(i -> distance[i]).toArray();
    }
}