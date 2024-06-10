import java.util.*;
class Solution {
    public List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList();
        Map<Integer, Integer> countMap = new HashMap();
        s = s.substring(1, s.length() - 1);
        String temp = "";
        for (char c: s.toCharArray()) {
            if (c == '{') {
                continue;
            }
            else if (c == '}' || c == ',') {
                if (temp == "") continue;
                int num = Integer.parseInt(temp);
                if (countMap.containsKey(num)) {
                    countMap.put(num, countMap.get(num) + 1);
                } else {
                    countMap.put(num, 1);
                }
                temp = "";
                continue;
            }
            temp += c;
        }
        
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        
        Collections.sort(entryList, (o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry: entryList) {
            answer.add(entry.getKey());
        }

        return answer;
    }
}