import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        int typeCount = new HashSet<>(Arrays.asList(gems)).size();
        HashMap<String, Integer> gemsMap = new HashMap();
        int start = 0, length = Integer.MAX_VALUE;
        for (int end = 0; end < gems.length; end++) {
            
            gemsMap.put(gems[end], gemsMap.getOrDefault(gems[end], 0) + 1);

            // 구간 가장 첫번째 원소가 두 번 이상 나오면 한 칸 이동
            while (gemsMap.get(gems[start]) > 1) {
                gemsMap.put(gems[start], gemsMap.get(gems[start]) - 1);
                start++;
            }
            
            // 모든 종류의 보석을 포함하면서 가장 짧은 구간 찾는다
            if (gemsMap.keySet().size() == typeCount && (end - start) < length) {
                length = end - start;
                answer = new int[] {start + 1, end + 1};
            }
        }
        
        return answer;
    }
}