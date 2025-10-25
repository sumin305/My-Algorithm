import java.io.*;
import java.util.*;

class Solution {
    
    List<String> words;
    String[] alpha = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        int answer = 0;
        words = new ArrayList<String>();
        makeWords(0, "");
        answer = words.indexOf(word) + 1;
        return answer;
    }
    
    public void makeWords(int idx, String str) {
        if (idx == 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            words.add(str + alpha[i]);
            makeWords(idx + 1, str + alpha[i]);
        }
    }
}