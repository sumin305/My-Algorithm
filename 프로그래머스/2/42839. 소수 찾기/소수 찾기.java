import java.util.*;
import java.io.*;
class Solution {
    
    Set<Integer> primeSet = new HashSet<Integer>();
    char[] numberArray;
    int length;
    boolean[] visited;
    
    public int solution(String numbers) {
        length = numbers.length();
        numberArray = numbers.toCharArray();
        visited = new boolean[length];
        
        dfs(0, "");
        return primeSet.size();
    }
    
    public void dfs(int idx, String str) {
        if (str != "") {        
            int number = Integer.parseInt(str);
            System.out.println(number + " " + isPrimeNumber(number));
            if (isPrimeNumber(number)) {
                primeSet.add(number);
            }
        }

        if (idx == length) {
            return;
        }
        
        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String newStr = str  + numberArray[i];
                dfs(idx + 1, newStr);
                visited[i] = false;
            }
        }
    }
    
    public boolean isPrimeNumber(int num) {
        if (num <= 1) return false;
        int i = 2; 
        while (i < num) {
            if (num % i++ == 0) return false;
        }
        return true;
    }
}