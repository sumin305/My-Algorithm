import java.util.*;
class Solution {
    int nums[], targetNum, count;
    public int solution(int[] numbers, int target) {
        count = 0;
        nums = numbers;
        targetNum = target;

        dfs(0, 0);
        return count;
    }
    
    public void dfs(int idx, int sum) {
        if (idx == nums.length) {
            if (sum == targetNum) {
                count++;
            }
            return;
        }
        
        dfs(idx + 1, sum + (-1 * nums[idx]));
        dfs(idx + 1, sum + (nums[idx]));
    } 
}