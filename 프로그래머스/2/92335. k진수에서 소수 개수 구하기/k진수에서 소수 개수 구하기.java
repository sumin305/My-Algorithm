import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String convertedStr = convert(n, k);
        String[] splitStr = convertedStr.split("0");
        for (String str: splitStr) {
            if (str.equals("")) {           
                continue;
            }
            if (isPrime(Long.parseLong(str))) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isPrime(Long n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
    
    public String convert(int n, int k) {
        int remain = n;
        StringBuilder sb = new StringBuilder();
        while (remain >= 1) {
            sb.append(remain % k);
            remain = remain / k;
        }
        return sb.reverse().toString();
    }
}