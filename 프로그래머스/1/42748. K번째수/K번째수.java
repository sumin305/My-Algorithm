import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = commands.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int startIdx = commands[i][0] - 1;
            int endIdx = commands[i][1] - 1;
            int position = commands[i][2] - 1;
            int[] tempArr = new int[endIdx - startIdx + 1];
            for (int j = 0; j < tempArr.length; j++) {
                tempArr[j] = array[startIdx + j];
            }
            Arrays.sort(tempArr);
            answer[i] = tempArr[position];
        }
        return answer;
    }
}