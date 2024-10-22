import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override 
            public int compare(Node n1, Node n2) {
                return n1.time - n2.time;
            }
        });
        
        boolean[][] visited = new boolean[n][m];
        pq.add(new Node(0, 0, 1));
        visited[0][0] = true;
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.x == n -1 && node.y == m - 1) {
                return node.time;
            }
            
            for (int v = 0; v < 4; v++) {
                int nx = node.x + dx[v];
                int ny = node.y + dy[v];
                
                if (onMap(nx, ny, n, m) && !visited[nx][ny] && maps[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    pq.add(new Node(nx, ny, node.time + 1));
                }
            }
        }
        
        return -1;
    }
    
    public boolean onMap(int x, int y, int n, int m) {
        return (0 <= x && x < n && 0 <= y && y < m);
    }
    
    public class Node {
        int x;
        int y;
        int time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}