import java.io.*;
import java.util.*;


public class BOJ1956 {

    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(graph[i], MAX);
            graph[i][i] = MAX;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (graph[i][k] != MAX && graph[k][j] != MAX) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        List<Integer> costs = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            costs.add(graph[i][i]);
        }
        int answer = Collections.min(costs);
        System.out.println(answer != MAX ? answer : -1);
    }
}