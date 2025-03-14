import java.io.*;
import java.util.*;


public class BOJ16928 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> ladders = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladders.put(x, y);
        }

        Map<Integer, Integer> snakes = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            snakes.put(u, v);
        }

        int answer = Integer.MAX_VALUE;
        int[] visited = new int[101];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1, 0});
        while (!dq.isEmpty()) {
            int[] cur = dq.pollLast();
            int curPos = cur[0];
            int count = cur[1];
            visited[curPos] = count;

            if (curPos == 100) {
                answer = Math.min(answer, count);
                continue;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPos = curPos + i;
                if (nextPos > 100) {
                    break;
                }
                
                if (ladders.getOrDefault(nextPos, -1) > 0) {
                    nextPos = ladders.get(nextPos);
                }
                if (snakes.getOrDefault(nextPos, -1) > 0) {
                    nextPos = snakes.get(nextPos);
                }

                if (visited[nextPos] > count + 1) {
                    dq.add(new int[]{nextPos, count + 1});
                }
            }
        }

        System.out.println(answer);
    }
}
