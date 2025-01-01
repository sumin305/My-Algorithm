import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        String answer = "";
        String temp = "";
        HashMap<String, Integer> wordMap = new HashMap();
        
        wordMap.put("zero", 0);
        wordMap.put("one", 1);
        wordMap.put("two", 2);
        wordMap.put("three", 3);
        wordMap.put("four", 4);
        wordMap.put("five", 5);
        wordMap.put("six", 6);
        wordMap.put("seven", 7);
        wordMap.put("eight", 8);
        wordMap.put("nine", 9);

        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                answer += c;
                continue;
            }
            
            temp += c;
            if (wordMap.keySet().contains(temp)) {
                answer += wordMap.get(temp);
                temp = "";
            } 
        }
        return Integer.parseInt(answer);
    }
}