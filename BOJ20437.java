import java.io.*;
import java.util.*;;

public class BOJ20437 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            Map<Character, Integer> dict = new HashMap<>();
            for (int i = 0; i < W.length(); i++) {
                int count = dict.getOrDefault(W.charAt(i), -1);
                if (count == -1) {
                    dict.put(W.charAt(i), 0);
                }
                dict.replace(W.charAt(i), dict.get(W.charAt(i)) + 1);
            }
            
            int maxCount = Collections.max(dict.values());
            if (maxCount < K) {  // 모든 문자가 K개보다 적은 경우
                System.out.println(-1);
                continue;
            }

            // 가장 짧은 문자열
            dict.clear();
            int left = 0;
            int right = 0;
            int minLength = Integer.MAX_VALUE;
            while (left <= right && right < W.length()) {
                char curChar = W.charAt(right);
                int count = dict.getOrDefault(curChar, -1);
                if (count < 0) {
                    dict.put(curChar, 1);
                }
                else {
                    dict.replace(curChar, dict.get(curChar) + 1);
                }

                if (dict.get(curChar) == K) {
                    while (W.charAt(left) != curChar) {
                        dict.replace(W.charAt(left), dict.get(W.charAt(left)) - 1);
                        left += 1;
                    }
                    minLength = Math.min(minLength, right - left + 1);
                    // System.out.println("left : " + left + " right: " + right);
                    left += 1;
                    dict.replace(curChar, dict.get(curChar) - 1);
                }

                right += 1;
            }

            // 가장 긴 문자열
            int maxLength = -1;
            Map<Character, Deque<Integer>> newDict = new HashMap<>();
            for (int i = 0; i < W.length(); i++) {
                char curChar = W.charAt(i);
                if (newDict.getOrDefault(curChar, null) == null) {
                    newDict.put(curChar, new ArrayDeque<>());
                }

                Deque<Integer> curDq = newDict.get(curChar);
                curDq.add(i);
                if (curDq.size() == K) {
                    int idx1 = curDq.getFirst();
                    int idx2 = curDq.getLast();
                    maxLength = Math.max(maxLength, idx2 - idx1 + 1);
                    curDq.pollFirst();
                }
            }

            System.out.println(minLength + " " + maxLength);
        }
    }
    
}
