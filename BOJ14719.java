import java.io.*;
import java.util.*;


public class BOJ14719 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] heights = new int[W];
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int height = 1; height <= H; height++) {
            int before = -1;
            for (int i = 0; i < W; i++) {
                if (heights[i] >= height) {
                    if (before == -1) {
                        before = i;
                        continue;
                    }
                    result += (i - before - 1);
                    before = i;
                }
            }
        }

        System.out.println(result);
    }
}
