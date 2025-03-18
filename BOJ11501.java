import java.io.*;
import java.util.*;


public class BOJ11501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] costs = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }
            
            boolean[] isHighest = new boolean[N];
            int curMax = costs[N - 1];
            isHighest[N - 1] = true;
            for (int i = N - 2; i >= 0; i--) {
                if (costs[i] > curMax) {
                    isHighest[i] = true;
                    curMax = costs[i];
                }
            }

            long answer = 0;
            List<Integer> curStocks = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (isHighest[i]) {
                    if (curStocks.isEmpty()) {
                        continue;
                    }

                    long profit = 0;
                    for (int j = 0; j < curStocks.size(); j++) {
                        profit += (costs[i] - curStocks.get(j));
                    }

                    if (profit > 0) {
                        curStocks.clear();
                        answer += profit;
                    }
                }
                else {
                    curStocks.add(costs[i]);
                }
            }

            System.out.println(answer);
        }
    }
}
