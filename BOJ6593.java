import java.io.*;
import java.util.*;


public class BOJ6593 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());  // 층
            int R = Integer.parseInt(st.nextToken());  // 행
            int C = Integer.parseInt(st.nextToken());  // 열

            // 종료
            if (L == 0 && R == 0 && C == 0) {
                return;
            }

            Character[][][] building = new Character[L][R][C];
            int[] startPos = new int[3];
            int[] endPos = new int[3];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = input.charAt(k);
                        if (input.charAt(k) == 'S') {
                            startPos[0] = i;
                            startPos[1] = j;
                            startPos[2] = k;
                        }
                        else if (input.charAt(k) == 'E') {
                            endPos[0] = i;
                            endPos[1] = j;
                            endPos[2] = k;
                        }
                    }
                }
                br.readLine();  // 공백
            }

            Boolean[][][] visited = new Boolean[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        visited[i][j][k] = false;
                    }
                }
            }
            visited[startPos[0]][startPos[1]][startPos[2]] = true;

            int[][] directions = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
            
            Deque<int[]> dq = new ArrayDeque<>();
            int[] startElem = {startPos[0], startPos[1], startPos[2], 0};
            dq.addLast(startElem);

            int minutes = 0;

            // BFS
            while (!dq.isEmpty()) {
                int[] cur = dq.removeFirst();
                int cl = cur[0];
                int cr = cur[1];
                int cc = cur[2];
                int cMin = cur[3];
                if (cl == endPos[0] && cr == endPos[1] && cc == endPos[2]) {
                    minutes = cMin;
                    break;
                }

                for (int i = 0; i < directions.length; i++) {
                    int nl = cl + directions[i][0];
                    int nr = cr + directions[i][1];
                    int nc = cc + directions[i][2];
                    // board 범위 내
                    if (nl >= 0 && nl < L && nr >= 0 && nr < R && nc >= 0 && nc < C) {
                        if (building[nl][nr][nc] != '#' && !visited[nl][nr][nc]) {
                            int[] nextPos = {nl, nr, nc, cMin + 1};
                            dq.addLast(nextPos);
                            visited[nl][nr][nc] = true;
                        }
                    }
                }
            }

            if (minutes == 0) {
                System.out.println("Trapped!");
            }
            else {
                System.out.printf("Escaped in %d minute(s).\n", minutes);
            }
        }
    }
}
