import java.io.*;
import java.util.*;


public class BOJ22866 {

    private static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N];
        int[] closest = new int[N];
        Arrays.fill(closest, -1);

        // 왼쪽 스택 처리
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                count[i] += stack.size();
                closest[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                count[i] += stack.size();
                if (closest[i] == -1 || Math.abs(i - closest[i]) > Math.abs(i - stack.peek())) {
                    closest[i] = stack.peek();
                } else if (Math.abs(i - closest[i]) == Math.abs(i - stack.peek())) {
                    // 거리가 같다면 번호가 작은 쪽
                    if (stack.peek() < closest[i]) {
                        closest[i] = stack.peek();
                    }
                }
            }
            stack.push(i);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (count[i] == 0) {
                sb.append("0\n");
            }
            else {
                sb.append(count[i]).append(" ").append(closest[i] + 1).append("\n");
            }
        }

        System.out.println(sb);
    }
}