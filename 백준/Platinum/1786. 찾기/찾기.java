import java.io.*;
import java.util.*;

public class Main {
	public static char[] text, pattern;
	public static int tl, pl;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text = br.readLine().toCharArray();
		pattern = br.readLine().toCharArray();
		
		tl = text.length;
		pl = pattern.length;
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		int[] table = makeTable();

		for (int i = 0, j = 0; i < tl; i++) {
			
			while (j > 0 && text[i] != pattern[j]) {
				j = table[j - 1];
			}
			
			if (text[i] == pattern[j]) {
				if (j == pl - 1) {
					count++;
					sb.append(i - j + 1 + " ");
					j = table[j];
				} else {
					j++;
				}
			} 

		}
		System.out.println(count);
		if (count > 0) System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
	}
	
	public static int[] makeTable() {
		int[] table = new int[pl];
		for (int i = 1, j = 0; i < pl; i++) {
			
			while (j > 0 && pattern[i] != pattern[j]) {
				j = table[j - 1];
			}
			
			if (pattern[i] == pattern[j]) {
				table[i] = ++j;
			} else {
				table[i] = 0;
			}
		}
		return table;
	}

}