class Solution {
    public int solution(int n, int w, int num) {
        int q = n / w;  // 층 수
        int r = n % w;
        if (r > 0) {
            q += 1;
        }
        
        int[][] map = new int[q][w];
        int count = 1;
        int[] start = new int[2];
        for (int i = 0; i < map.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = count;
                    if (count == num) {
                        start[0] = i;
                        start[1] = j;
                    }
                    count += 1;
                }
            }
            else {
                for (int j = map[0].length - 1; j >= 0; j--) {
                    map[i][j] = count;
                    if (count == num) {
                        start[0] = i;
                        start[1] = j;
                    }
                    count += 1;
                }
            }
        }
        
        
        int answer = 1;
        for (int i = start[0] + 1; i < map.length; i++) {
            if (map[i][start[1]] <= n) {
                answer += 1;
            }
        }
        
        
        return answer;
    }
}
