import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList();
        Map<String, Integer> termsMap = new HashMap<>();
        
        // ex) "A 6"
        // terms를 HashMap으로
        for (String term: terms) {
            String[] t = term.split(" ");
            termsMap.put(t[0], Integer.parseInt(t[1]));
        }
        int[] t = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();

        // ex) "2021.05.02 A"
        int idx = 1;
        for (String privacy: privacies) {
            
            //arr[0] : 2021.05.02
            //arr[1] : A
            String[] arr = privacy.split("\\ ");
            
            int[] day = Arrays.stream(arr[0].split("\\."))
                            .mapToInt(Integer::parseInt).toArray();
            
            int[] calcDay = calc(day, termsMap.get(arr[1]));
            
            System.out.println(Arrays.toString(calcDay));
            System.out.println(Arrays.toString(t));

            if (!compareToToday(t, calcDay)) {
                answer.add(idx);
            }
            
            System.out.println(idx + " " + compareToToday(t, calcDay));
            
            idx++;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] calc(int[] day, int m) {
        int[] calcDay = day;
        
        
        // 유효기간이 그 해를 넘어갈때
        if(day[1] + m > 12) {
            if ((day[1] + m) % 12 == 0) {
                calcDay[0] = calcDay[0] + (day[1] + m) / 12 - 1;
            } else {
                calcDay[0] = calcDay[0] + (day[1] + m) / 12;
                calcDay[1] = calcDay[1] + (day[1] + m) % 12;
            }
            
        } else {
            calcDay[1] = calcDay[1] + m;
        }
        
        // 일 계산
        if (calcDay[2] == 1) {
            if (calcDay[1] == 1) {
                calcDay[0]--;
                calcDay[1] = 12;
            } else {
                calcDay[1]--;
            }
            calcDay[2] = 28;
        } else {
            calcDay[2]--;
        }
            
        return calcDay;
    }
    
    // 유효기간 지났으면 false 지나지 않았으면 true (day >= today)
    public boolean compareToToday(int[] today, int[] day) {
        if (today[0] > day[0]) return false;
        if (today[0] < day[0]) return true;
        if (today[1] > today[1]) return false;
        if (today[1] < today[1]) return true;
        if (today[2] > day[2]) return false;
        return true;
    }
}