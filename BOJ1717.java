import java.io.*;
import java.util.*;


public class BOJ1717 {

    private static int[] parents;
    private static int getParent(int a) {
        if (parents[a] == a) {
            return a;
        }

        parents[a] = getParent(parents[a]);
        return parents[a];
    }

    private static void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if (parentA != parentB) {
            // 더 작은 숫자를 부모로
            if (parentA < parentB) {
                parents[parentB] = parentA;
            }
            else {
                parents[parentA] = parentB;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // union
            if (option == 0) {
                union(a, b);
            }
            // 같은 집합에 있는지 검사
            else {
                if (getParent(a) == getParent(b)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }
    }
    
}
