import java.util.*;
import java.io.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> discountMap = new HashMap();
        
        for (String w: want) {
            discountMap.put(w, 0);
        }
        
        int first = 0;

        for (int i = 0; i < 10; i++) {
            if (discountMap.containsKey(discount[i])) {
                discountMap.put(discount[i], discountMap.get(discount[i]) + 1);
            } 
        }
        
        if (canJoin(discountMap, want, number)) answer++;
        
        for (int i = 10; i < discount.length; i++) {
            String firstStr = discount[first++];
            if (discountMap.containsKey(
                firstStr
            )) {
                discountMap.put(firstStr, discountMap.get(firstStr) - 1);
            }
            
            if (discountMap.containsKey(
                discount[i]
            )) {
                discountMap.put(discount[i], discountMap.get(discount[i]) + 1);
            }
            if (canJoin(discountMap, want, number)) answer++;
        }
        return answer;
    }
     
    public boolean canJoin(Map<String, Integer> discountMap, String[] want, int[] number) {
        int idx = 0;
        for (String w: want) {
            if (discountMap.get(w) != number[idx++]) {
                return false;
            }
        }
        return true;
    }
}