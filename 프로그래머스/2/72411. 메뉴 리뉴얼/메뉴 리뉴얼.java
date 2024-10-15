import java.util.*;
import java.io.*;
import java.util.stream.Collectors;  

class Solution {
    HashMap<String, Integer> orderMap;
    HashMap<Integer, Integer> orderMaxCount;
    boolean[] selected;
    String target;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<String>();
        orderMap = new HashMap();
        orderMaxCount = new HashMap();
        for (String order: orders) {
            for (int c: course) {
                // 해당 order을 c개 선택해 조합한 후, HashMap에 추가
                
                char[] orderArray = order.toCharArray();
                Arrays.sort(orderArray);
                int count = c;
                
                selected = new boolean[orderArray.length];
                target = "";
                
                combi(orderArray, count, 0, 0);
            }
        }
        
            
        List<String> resultList = orderMap.keySet()
            .stream()
            .filter(key -> orderMap.get(key) > 1)
            .sorted()
            .collect(Collectors.toList()); 

        // 여기서 각 자릿수 별 최대 
        
        for (int c: course) {
            orderMaxCount.put(c, 0);
        }
        for (String order: resultList) {
            int cnt = order.length();
            orderMaxCount.put(cnt, Integer.max(orderMaxCount.get(cnt), orderMap.get(order))); 
        }
       for (String order: resultList) {
           int cnt = order.length();
           if (orderMaxCount.get(cnt) == orderMap.get(order)) {
               answer.add(order);
           }  
        }
        return answer.toArray(new String[answer.size()]);
    }
    
    public void combi(char[] order, int count, int idx, int start) {
        if (idx == count) {
            System.out.println(target);
            orderMap.put(target, orderMap.getOrDefault(target, 0) + 1);
            return;
        }
        
        for (int i = start; i < order.length; i++) {
            if (!selected[i]) {
                selected[i] = true;
                target += order[i];
                combi(order, count, idx + 1, i + 1);
                target = target.substring(0, target.length() - 1);
                selected[i] = false;
            }
        }
    }
}