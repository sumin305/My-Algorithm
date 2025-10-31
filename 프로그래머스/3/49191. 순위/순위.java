import java.util.*;
class Solution {
    boolean[] isVisited;
    List<Integer>[] winList;
    List<Integer>[] loseList;
    int[] winCount, loseCount;
    public int solution(int n, int[][] results) {
        int answer = 0;
        isVisited = new boolean[n + 1];
        winList = new List[n + 1]; // 해당 노드 이긴 노드들
        loseList = new List[n + 1];
        winCount = new int[n + 1];
        loseCount = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            winList[i] = new ArrayList<Integer>();
            loseList[i] = new ArrayList<Integer>();
        }
        
        for (int[] result: results) {
            int a = result[0];
            int b = result[1];
            
            winList[b].add(a);
            loseList[a].add(b);
        }
        for (int i = 1; i <= n; i++) {
            isVisited = new boolean[n + 1];
            winGraph(i, i);
            isVisited = new boolean[n + 1];
            loseGraph(i, i);
        }

        for (int i = 1; i <= n; i++) {
            if (winCount[i] + loseCount[i] == n - 1) answer++;
        }
        
        return answer;
    }
    
    public void winGraph(int n, int next) {
        isVisited[next] = true;
        for (Integer adj: winList[next]) {
            if (!isVisited[adj]) {
                winCount[n]++;
                winGraph(n, adj);
            }
        }
    }
    
    public void loseGraph(int n, int next) {
        isVisited[next] = true;
        for (Integer adj: loseList[next]) {
            if (!isVisited[adj]) {
                loseCount[n]++;
                loseGraph(n, adj);
            }
        }
    }
}
