import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int num = 1;
        int count = 1;
        while (num < N) {
            num = num + 6 * count ++;
        }
        System.out.println(count);
    }
}