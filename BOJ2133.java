import java.io.*;


public class BOJ2133 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 가로 길이가 홀수라면 모두 채울 수 없음
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 4; j < i; j += 2) {
                dp[i] += dp[i - j] * 2;
            }
            dp[i] += 2;
        }
        System.out.println(dp[N]);
    }
}
