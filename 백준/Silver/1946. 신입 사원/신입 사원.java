import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine()); // 지원자 수 입력
            int[][] applicants = new int [N][2];
            for (int i = 0; i < N; i++) {
                applicants[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            // 서류심사 성적 기준으로 정렬
            Arrays.sort(applicants, (s1, s2) -> {
                return s1[0] - s2[0];
            });
            int temp = applicants[0][1];
            int count = 1;
            for (int[] applicant: applicants) {
                if (applicant[0] == 1) continue; // 서류심사 성적이 1등인 참가자는 이미 선발
                if (applicant[1] > temp) continue; // 서류심사 성적 기준으로 정렬했을 때, 면접시험 등수가 이전 항목들보다 낮다면, 선발할 수 없음.
                count++;
                temp = Math.min(temp, applicant[1]);
            }
            System.out.println(count);
        }
    }
}