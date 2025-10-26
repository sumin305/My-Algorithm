import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<Node> queue = new ArrayDeque();
        queue.add(new Node(begin, 0));
        
        while (!queue.isEmpty()) {
            
            Node targetNode = queue.poll();
            System.out.println(targetNode.word + " " + targetNode.count);
                        
            // target과 동일한 단어일 경우 return
            if (targetNode.word.equals(target)) {
                return targetNode.count;
            }
            
            // words에 바꿀 수 있는 단어 있나 확인
            for (String word: words) {
                if (canChange(word, targetNode.word)) {
                    queue.add(new Node(word, targetNode.count + 1));
                }
            }

            if (targetNode.count > words.length) break;
        }
        return answer;
    }
    
    // 한글자만 다른지 확인
    public boolean canChange(String word1, String word2) {
        int cnt = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) cnt++;
        }
        return cnt == 1;
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