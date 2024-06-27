import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 1;
        while (n > 1) {
            result *= n;
            n--;
        }
        System.out.println(result);
    }
}