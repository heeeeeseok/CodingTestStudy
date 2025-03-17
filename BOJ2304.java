import java.io.*;
import java.util.*;


public class BOJ2304 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<int[]> cols = new ArrayList<>();
        StringTokenizer st;
        int maxHegith = -1;
        List<Integer> maxIdxs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            cols.add(new int[]{L, H});

            if (H > maxHegith) {
                maxIdxs.clear();
                maxHegith = H;
                maxIdxs.add(L);
            }
        }

        Collections.sort(cols, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return Integer.compare(arr1[0], arr2[0]);
            }
        });

        int maxLeft = 0;
        int maxRight = 0;
        if (maxIdxs.size() == 1) {
            maxLeft = maxIdxs.get(0);
            maxRight = maxLeft;
        }
        else {
            Collections.sort(maxIdxs);
            maxLeft = maxIdxs.get(0);
            maxRight = maxIdxs.get(maxIdxs.size() - 1);
        }

        int area = 0;
        int beforeIdx = 0;
        int beforeHeight = 0;
        for (int i = 0; i < cols.size(); i++) {
            int idx = cols.get(i)[0];
            int height = cols.get(i)[1];

            int width = idx - beforeIdx - 1;
            area += width * beforeHeight;

            if (idx == maxLeft) {
                break;
            }

            beforeIdx = idx;
            if (height > beforeHeight) {
                area += height;
                beforeHeight = height;
            }
            else {
                area += beforeHeight;
            }
        }

        beforeIdx = 0;
        beforeHeight = 0;
        for (int i = cols.size() - 1; i >= 0; i--) {
            int idx = cols.get(i)[0];
            int height = cols.get(i)[1];

            int width = beforeIdx - idx - 1;
            area += width * beforeHeight;


            if (idx == maxRight) {
                break;
            }

            beforeIdx = idx;
            if (height > beforeHeight) {
                area += height;
                beforeHeight = height;
            }
            else {
                area += beforeHeight;
            }
        }

        int width = maxRight - maxLeft + 1;
        area += width * maxHegith;

        System.out.println(area);
    }
}
