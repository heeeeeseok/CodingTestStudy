import java.util.*;
import java.io.*;


public class BOJ6087 {
    
    private static char[][] board;
    private static int[][][] visited;
    private static int answer = Integer.MAX_VALUE;
    private static int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        board = new char[H][W];
        int[] start = {-1, -1};
        int[] end = {-1, -1};
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'C') {
                    if (start[0] == -1 && start[1] == -1) {
                        start[0] = i;
                        start[1] = j;
                    }
                    else {
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
        }

        visited = new int[4][H][W];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        // dirsIdx, dy, dx, count
        Deque<int[]> dq = new LinkedList<>();
        for (int i = 0; i < dirs.length; i++) {
            visited[i][start[0]][start[1]] = 0;
            int[] cur = {i, start[0], start[1], 0};
            dq.add(cur);
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int dirsIdx = cur[0];
            int cy = cur[1];
            int cx = cur[2];
            int count = cur[3];

            if (count >= answer) {
                continue;
            }
            if (cy == end[0] && cx == end[1]) {
                answer = Math.min(answer, count);
                continue;
            }

            for (int i = 0; i < dirs.length; i++) {
                int ny = cy + dirs[i][0];
                int nx = cx + dirs[i][1];
                // 범위 내
                if (ny >= 0 && ny < board.length && nx >= 0 && nx < board[0].length) {
                    // 반대방향으로는 이동하지 않음
                    if (dirs[dirsIdx][0] == dirs[i][0] * -1 && dirs[dirsIdx][1] == dirs[i][1] * -1) {
                        continue;
                    }

                    // 이동 가능, 방문하지 않음
                    if (board[ny][nx] != '*') {
                        int nextCount = count;
                        // 이전 방향과 다른 방향이면 거울 추가
                        if (dirsIdx != i) {
                            nextCount += 1;
                        }

                        if (visited[dirsIdx][ny][nx] > nextCount) {
                            visited[dirsIdx][ny][nx] = nextCount;
                            dq.add(new int[]{i, ny, nx, nextCount});
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
