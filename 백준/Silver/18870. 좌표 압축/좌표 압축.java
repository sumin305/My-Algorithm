import java.nio.Buffer;
import java.util.*;
import java.io.*;
public class Main {
 public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     // 나보다 작은 수의 개수 구하기
     int N = Integer.parseInt(br.readLine());
     List<Integer> list = new ArrayList<>();
     List<Integer> sortedList = new ArrayList<>();
     HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
     StringTokenizer st = new StringTokenizer(br.readLine());
     for (int i = 0; i < N; i++) {
         int n = Integer.parseInt(st.nextToken());
         list.add(n);
         sortedList.add(n);
     }
     Collections.sort(sortedList);
     int idx = 0;
     for (int a: sortedList) {
    	 if (!hashMap.containsKey(a)) {
    		 hashMap.put(a, idx++);
    	 }
     }
     StringBuilder sb = new StringBuilder();

     for (int a: list) {
    	 int temp = hashMap.get(a);
    	 sb.append(temp).append(" ");
     }
     System.out.println(sb);
 }
}