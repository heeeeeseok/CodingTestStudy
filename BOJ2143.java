import java.io.*;
import java.util.*;


public class BOJ2143 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        
        int[] array1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array1[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] array2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            array2[i] = Integer.parseInt(st.nextToken());
        }

        long[] array1Sum = new long[n + 1];
        Map<Long, Long> sumMap1 = new HashMap<>();
        array1Sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            array1Sum[i] = array1[i - 1] + array1Sum[i - 1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                long curNum = array1Sum[j] - array1Sum[i];
                sumMap1.put(curNum, 1 + sumMap1.getOrDefault(curNum, 0L));
            }
        }

        long[] array2Sum = new long[m + 1];
        Map<Long, Long> sumMap2 = new HashMap<>();
        array2Sum[0] = 0;
        for (int i = 1; i <= m; i++) {
            array2Sum[i] = array2[i - 1] + array2Sum[i - 1];
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                long curNum = array2Sum[j] - array2Sum[i];
                sumMap2.put(curNum, 1 + sumMap2.getOrDefault(curNum, 0L));
            }
        }

        long answer = 0;
        for (Long curNum : sumMap1.keySet()) {
            long target = T - curNum;
            answer += sumMap1.get(curNum) * sumMap2.getOrDefault(target, 0L);
        }

        System.out.println(answer);
    }
}
