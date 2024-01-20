import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();
        Set<String> startAttendeeSet = new HashSet<>();
        Set<String> attendeeSet = new HashSet<>();
        String str = null;
        while ((str = br.readLine()) != null) {
            String[] s = str.split(" ");
            String time = s[0];
            String nickName = s[1];

            // 스트리밍 전 입장인가 (입장한 시간이 스트리밍 시작 시간보다 작거나 같음)
            if (S.compareTo(time) >= 0) {
                startAttendeeSet.add(nickName);
            }
            // 스트리밍 종료 후 퇴장인가 (E <= time <= Q)
            if (E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) {
                if (startAttendeeSet.contains(nickName)) {
                    attendeeSet.add(nickName);
                }
            }
        }
        System.out.println(attendeeSet.size());
    }
}