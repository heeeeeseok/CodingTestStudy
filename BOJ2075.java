import java.io.*;
import java.util.*;


public class BOJ2075 {

    private static final int MIN = -1000000001;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j ++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> stack = new ArrayList<>();
        int[] rowIdxs = new int[n];
        Arrays.fill(rowIdxs, n - 1);
        
        while (stack.size() != n) {
            int curMax = MIN;
            int maxIdx = -1;
            for (int i = 0; i < n; i++) {
                if (rowIdxs[i] >= 0 && board[rowIdxs[i]][i] > curMax) {
                    curMax = board[rowIdxs[i]][i];
                    maxIdx = i;
                }
            }
            stack.add(curMax);
            rowIdxs[maxIdx] -= 1;
        }

        System.out.println(stack.get(n - 1));
    }
}
