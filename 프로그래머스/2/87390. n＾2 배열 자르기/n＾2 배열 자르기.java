import java.util.*; 
class Solution {
    public List<Long> solution(int n, long left, long right) {
        List<Long> result = new ArrayList();
        int idx = 0;
        for (long i = left; i <= right; i++) {
            long x = i / n;
            long y = i % n;
            result.add(Math.max(x, y) + (long)1);
        }
        return result;
    }
}