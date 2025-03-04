import java.io.*;
import java.util.*;


public class BOJ17609 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            int count = solve(input);

            if (count == 0) {
                System.out.println(0);
            }
            else if (count == 1) {
                System.out.println(1);
            }
            else {
                System.out.println(2);
            }
        }
    }

    private static int solve(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left += 1;
                right -= 1;
                continue;
            }

            if (left + 1 < right) {
                int case1 = -1;
                int case2 = -1;
                if (s.charAt(left + 1) == s.charAt(right)) {
                    case1 = 1;
                    case1 += solve(s.substring(left + 2, right));
                }
                if (s.charAt(left) == s.charAt(right - 1)) {
                    case2 = 1;
                    case2 += solve(s.substring(left + 1, right - 1));
                }
                if (case1 < 0 && case2 < 0) {
                    return 2;
                }
                else if (case1 < 0) {
                    return case2;
                }
                else if (case2 < 0) {
                    return case1;
                }
                else {
                    return Math.min(case1, case2);
                }
            }
            if (left + 1 == right) {
                return 1;
            }
            
            return 2;
        }

        return 0;
    }
}
