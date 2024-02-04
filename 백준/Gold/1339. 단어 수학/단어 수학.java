import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];
        Map<Character, Integer> weightMap = new HashMap<>();
        Map<Character, Integer> alphaMap = new HashMap<>();
        int sum = 0;
        int num = 9;

        // 각 알파벳 개수 세고 & 알파벳을 자릿수에 해당하는 digitArr 배열에 넣는다.
        for (int i = 0; i < N; i ++) {
            strArr[i] = br.readLine();
            for (int idx = 0; idx < strArr[i].length();idx++) {
                char c  = strArr[i].charAt(idx);
                int digit = strArr[i].length() - idx - 1; // 해당 알파벳 자리수 (ACV -> A: 2, C: 1, V: 0)
                if (!weightMap.containsKey(c)) weightMap.put(c, 0);
                weightMap.put(c, (int)(weightMap.get(c) + Math.pow(10, digit)));
            }
        }

        Object[] charArray = weightMap.keySet().toArray();

        // 가중치 높은 순으로 정렬
        Arrays.sort(charArray, (s1, s2) -> weightMap.get(s2) - weightMap.get(s1));

        for (Object o: charArray) {
            char c = (char)o;
            if (!alphaMap.containsKey(c)) weightMap.put(c, 0);
            alphaMap.put(c, num--);
        }
        for(String s: strArr) {
            for (int idx = 0; idx < s.length(); idx++) {
                char c = s.charAt(idx); // 해당 자리에 해당하는 문자
                int digit = s.length() - idx - 1;
                sum += (alphaMap.get(c) * (Math.pow(10, digit)));
            }
        }
        System.out.println(sum);
    }
}