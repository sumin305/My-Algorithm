import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[1] == arr2[1]) {
                    return arr1[0] - arr2[0];
                }
                return arr1[1] - arr2[1];
            }
        });
            
        int beforeCamera = Integer.MIN_VALUE;
        
        for (int[] car: routes) {
            if (car[0] <= beforeCamera && beforeCamera <= car[1]) {
                continue;
            }
            beforeCamera = car[1];
            answer++;
        }
        return answer;
    }
}