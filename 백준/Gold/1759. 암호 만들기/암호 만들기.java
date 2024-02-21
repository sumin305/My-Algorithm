import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] alpha;
	static ArrayList<Character> consonant, vowel;
	static HashSet<String> result;
	static HashSet<Character> used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] t = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		L = t[0];
		C = t[0];
		result = new HashSet<>();
		
		String[] stringAlpha = br.readLine().split(" ");
		alpha = new char[stringAlpha.length];
		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = stringAlpha[i].charAt(0);
		}
		used = new HashSet<>(); // 사용된 알파벳
	    consonant = new ArrayList<>();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		vowel = new ArrayList<>();       
		selected = new char[2];
		for (char a: alpha) {
			if(a =='a' || a == 'e' || a == 'o' || a == 'u' || a == 'i') vowel.add(a);
			else consonant.add(a);
		}
		visited = new boolean[consonant.size()]; // 자음 조합하기 위한 방문 기록

		selectConsonant(0, 0);
		
		ArrayList<String> r = new ArrayList<>(result);
		Collections.sort(r);
		
		for (String s: r) {
			System.out.println(s);
		}
		
	}
	static boolean[] visited;
	static char[] selected;
	static ArrayList<Character> remainders;
	public static void selectConsonant(int idx, int start) {
		 if (idx == 2) {
			// 자음 선택 
			 for (int i = 0; i < vowel.size(); i++) {
				 used.add(vowel.get(i));
				remainders = new ArrayList<>();
				 for (char a: alpha) {
					 if (!used.contains(a)) {
						 remainders.add(a);
					 }
				 }
				remainders_visited = new boolean[remainders.size()];
				remainders_selected = new char[L - 3];

				 // 남은 것들끼리 조합 (remainders에서 (L - 3)개 뽑아서 used에 넣자)
				 selectRemainders(0, 0);
				 used.remove(vowel.get(i));
			 }
			 return;
		}
		 
		 for (int i = start; i < consonant.size(); i++) {
			 if (!visited[i]) {
				 visited[i] = true;
				 selected[idx] = consonant.get(i);
				 used.add(consonant.get(i));
				 selectConsonant(idx + 1, i + 1);
				 visited[i] = false;
				 used.remove(consonant.get(i));
			 }
		 }
	}
	
	static boolean[] remainders_visited;
	static char[] remainders_selected;

	public static void selectRemainders(int idx, int start) {
		 if (idx == L - 3) {
			 // result에 넣는다
			 char[] tempResult = new char[L];	
			 int charIdx = 0;
			 for (char u: used) {
				 tempResult[charIdx++] = u;
			 }
			 Arrays.sort(tempResult);
			 result.add(new String(tempResult));
			 return;
		}
		 
		 for (int i = start; i < remainders.size(); i++) {
			 if (!remainders_visited[i]) {

				 remainders_visited[i] = true;
				 remainders_selected[idx] = remainders.get(i);
				 used.add(remainders.get(i));
				 selectRemainders(idx + 1, i + 1);
				 remainders_visited[i] = false;
				 used.remove(remainders.get(i));
			 }
		 }
    }
}