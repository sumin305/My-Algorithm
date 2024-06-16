import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        String strA = str1.toLowerCase();
        String strB = str2.toLowerCase();
        HashMap<String, Integer> strAMap = new HashMap();
        HashMap<String, Integer> strBMap = new HashMap();
        int intersectionCount = 0;
        int unionCount = 0;

        // strA, B 집합 구하기
        for (int i = 0; i < strA.length() - 1; i++) {
            String subStr = strA.substring(i, i + 2);
            if (Character.isLetter(subStr.charAt(0)) && Character.isLetter(subStr.charAt(1))) {
                if (strAMap.containsKey(subStr)) {
                    strAMap.put(subStr, strAMap.get(subStr) + 1);
                }  else {
                    strAMap.put(subStr, 1);
                }
            }
        }
        for (int i = 0; i < strB.length() - 1; i++) {
            String subStr = strB.substring(i, i + 2);
            if (Character.isLetter(subStr.charAt(0)) && Character.isLetter(subStr.charAt(1))) {
                if (strBMap.containsKey(subStr)) {
                    strBMap.put(subStr, strBMap.get(subStr) + 1);
                }  else {
                    strBMap.put(subStr, 1);
                }
            }
        }

        for (String key: strAMap.keySet()) {
            if (strBMap.containsKey(key)) {
                if (strAMap.get(key) == 1 && strBMap.get(key) == 1) {
                    intersectionCount ++;
                } else {
                    intersectionCount += (Math.min(strAMap.get(key), strBMap.get(key)));
                }
            } 

        }

        int totalA = 0, totalB = 0;
        for (Integer n: strAMap.values()) {
            totalA += n;
        }
        for (Integer n: strBMap.values()) {
            totalB += n;
        }
        unionCount = (totalA + totalB - intersectionCount);
        return unionCount == 0 ? 65536 : (int)((double)intersectionCount / (double)unionCount * (double)65536);
    }
}