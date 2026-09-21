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

            if (compareToToday(t, calcDay)) {
                System.out.println(compareToToday(t, calcDay) + " " + idx);
                answer.add(idx);
            }
                        
            idx++;
        }
        System.out.println("answer: " + answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // day 날짜 m 더하려는 달
    public int[] calc(int[] day, int m) {
        System.out.println("함수1검증: " + " " + Arrays.toString(day) + " " + m);
        
        int[] calcDay = day;
        
        // 유효기간이 그 해를 넘어갈때
        // 월 계산
        if(day[1] + m > 12) {
            if ((day[1] + m) % 12 == 0) {
                calcDay[0] = calcDay[0] + (day[1] + m) / 12 - 1;
                calcDay[1] = 12;
            } else {
                int sumYear = (day[1] + m) / 12;
                calcDay[0] = calcDay[0] + sumYear;
                calcDay[1] = (day[1] + m) - 12 * sumYear ;
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
            
        System.out.println(Arrays.toString(calcDay));
        return calcDay;
    }
    
    // today 오늘날짜 day 유효기간 만료 날짜 
    // 유효기간 지나면 true 아니면 false 반환
    public boolean compareToToday(int[] today, int[] day) {
        System.out.println(Arrays.toString(today) + " " + Arrays.toString(day));
        if (today[0] > day[0]) return true;
        if (today[0] < day[0]) return false;
        if (today[1] > day[1]) return true;
        if (today[1] < day[1]) return false;
        if (today[2] > day[2]) return true;
        return false;
    }
}