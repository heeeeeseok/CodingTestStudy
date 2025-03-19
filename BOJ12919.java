import java.io.*;


public class BOJ12919 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        String target = br.readLine();

        System.out.println(solve(start, target));
    }

    private static int solve(String start, String target) {
        if (start.length() == target.length()) {
            if (start.equals(target)) {
                return 1;
            }
            else {
                return 0;
            }
        }

        int lastIdx = target.length() - 1;
        Character c = target.charAt(lastIdx);
        // 방법 2만 가능한 경우
        if (c == 'B') {
            StringBuffer sb = new StringBuffer(target);
            target = sb.reverse().toString();  // 문자열 뒤집기
            if (target.charAt(lastIdx) == 'A') {  // 불가능
                return 0;
            }
        }
        // 방법 1, 2 모두 가능한 경우
        else if (target.charAt(0) == 'B') {
            int result1 = solve(start, target.substring(0, lastIdx));
            StringBuffer sb = new StringBuffer(target);
            target = sb.reverse().toString();  // 문자열 뒤집기
            int result2 = solve(start, target.substring(0, lastIdx));
            
            return Math.max(result1, result2);
        }
        // 방법 1만 가능한 경우
        return solve(start, target.substring(0, lastIdx));
    }
}
