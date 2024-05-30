import java.util.*;
import java.io.*;
class Solution {
    public String solution(String s) {
        int[] numArr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        
        for (int num: numArr) {
            if (num > maxValue) {
                maxValue = num;
            }
            if (num < minValue) {
                minValue = num;
            }
        }
        return minValue + " " + maxValue;
    }
}