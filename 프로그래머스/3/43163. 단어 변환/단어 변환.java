import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int length = words.length;
        Queue<Node> q = new ArrayDeque();
        boolean[] visited = new boolean[length];
        
        q.add(new Node(begin, 0));
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.word.equals(target)) return n.count;
            
            for (int i = 0; i < length; i++) {
                String next = words[i];
                if (isOneDifferent(n.word, next, next.length()) && !visited[i]) {
                    visited[i] = true;
                    q.add(new Node(next, n.count + 1));
                }
            }
            
        }
        return answer;
    }
    
    public boolean isOneDifferent(String a, String b, int length) {
        int cnt = 0;
        boolean result = false;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) cnt++;
        }
        result = cnt == 1 ? true : false;
        return result;
    }
}

class Node {
    String word;
    int count;
    
    Node(String word, int count) {
        this.word = word;
        this.count = count;
    }
}