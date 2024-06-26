import java.util.*;
class Solution {
    public List<Integer> solution(String msg) {
        List<Integer> answer = new ArrayList();
        HashMap<String, Integer> alphaMap = new HashMap();
        char[] msgArr = msg.toCharArray();
        int mapIdx = 27;
        int idx = 0;
        String temp = "";
        for (int i = 1; i <= 26; i++) {
            alphaMap.put(String.valueOf((char)(i + 64)), i);
        }
        System.out.println("test" + alphaMap.get("O"));
        while (idx < msgArr.length) {
            temp = String.valueOf(msgArr[idx]);
            System.out.println("first temp: " + temp);
            while (alphaMap.containsKey(temp) && idx + 1 < msgArr.length) {
                idx++;
                temp += msgArr[idx];
            }
            System.out.println("추가할 글자: " + temp);
            if (!alphaMap.containsKey(temp)) {
                alphaMap.put(temp, mapIdx++);
                temp = temp.substring(0, temp.length() - 1);
                System.out.println("정답에 넣기:" + temp);
                answer.add(alphaMap.get(temp));
            } else {
                answer.add(alphaMap.get(temp));
                break;
            }
            // System.out.println(alphaMap);
        }
        return answer;
    }
}