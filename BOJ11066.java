import java.io.*;
import java.util.*;

public class BOJ11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K];
            int[] prefixSum = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                prefixSum[i + 1] = prefixSum[i] + files[i];
            }

            // dp[start][end]
            int[][] dp = new int[K][K + 1];
            for (int length = 2; length <= K; length++) {
                for (int start = 0; start + length <= K; start++) {
                    int end = start + length;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int k = start + 1; k < end; k++) {
                        int cost = dp[start][k] + dp[k][end] + prefixSum[end] - prefixSum[start];
                        dp[start][end] = Math.min(dp[start][end], cost);
                    }
                }
            }

            sb.append(dp[0][K]).append("\n");
        }

        System.out.println(sb);
    }
}
