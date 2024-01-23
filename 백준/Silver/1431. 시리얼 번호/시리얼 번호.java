import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        class StringComparator implements Comparator<String> {
            public int addNumbers(String s) {
                int sum = 0;
                for(int i = 0; i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i))) {
                        sum += Character.getNumericValue(s.charAt(i));
                    }
                }
                return sum;
            }
            @Override
            public int compare(String s1, String s2) {
                int result = Integer.compare(s1.length(), s2.length());
                if (result != 0) {
                    return result;
                } else if (addNumbers(s1) != addNumbers(s2)) {
                    return addNumbers(s1) - addNumbers(s2);
                } else {
                    return s1.compareTo(s2);
                }
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }
        Collections.sort(list, new StringComparator());
        for (int i = 0; i < N; i++) {
            System.out.println(list.get(i));
        }
    }
}
