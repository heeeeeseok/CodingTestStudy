import java.io.*;
import java.util.*;


public class BOJ20055 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] belts = new int[2 * n];  // 내구도
        int[] robots = new int[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < belts.length; i++) {
            belts[i] = Integer.parseInt(st.nextToken());
        }

        int upPos = 0;
        int downPos = n - 1;
        int round = 0;
        int count = 0;

        // 4. 내구도 0인 벨트가 k개 이상이면 종료.
        while (count < k) {
            // 단계 카운팅
            round += 1;

            // 1. 벨트가 각 칸 위의 로봇과 함께 한 칸 회전
            upPos = getBeforeIdx(upPos, n);
            downPos = getBeforeIdx(downPos, n);

            // 2. 가장 먼저 벨트에 올라간 로봇부터 이동
            if (robots[downPos] == 1) {  // 내리는 위치라면 바로 내린다.
                robots[downPos] = 0;
            }

            int i = downPos;
            // downPos ~ upPos 까지 순회
            while (i != getBeforeIdx(upPos, n)) {
                if (robots[i] == 1) {
                    int nextPos = getNextIdx(i, n);
                    if (robots[nextPos] == 0 && belts[nextPos] > 0) {
                        robots[i] = 0;
                        robots[nextPos] = 1;
                        belts[nextPos] -= 1;
                        // 내리는 위치라면 바로 내린다.
                        if (nextPos == downPos) {
                            robots[nextPos] = 0;
                        }
                        // 내구도가 0인 벨트 카운팅
                        if (belts[nextPos] == 0) {
                            count += 1;
                        }
                    }
                }
                i = getBeforeIdx(i, n);
            }

            // 3. 올리는 위치에 로봇을 올린다.
            if (belts[upPos] > 0) {
                robots[upPos] = 1;
                belts[upPos] -= 1;
                // 내구도가 0인 벨트 카운팅
                if (belts[upPos] == 0) {
                    count += 1;
                }
            }
        }

        System.out.println(round);
    }
        

    public static int getNextIdx(int idx, int n) {
        return (idx + 1) % (2 * n);
    }

    public static int getBeforeIdx(int idx, int n) {
        int result = (idx - 1) % (2 * n);
        return result >= 0 ? result : result + (2 * n);
    }
}
