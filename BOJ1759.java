import java.io.*;
import java.util.*;


public class BOJ1759 {

    private static String[] alphabets;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 암호는 최소 한개의 모음과 최소 두개의 자음으로 구성됨
        // 암호는 오름차순 정렬 상태

        alphabets = new String[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken();
        }
        Arrays.sort(alphabets);

        List<String> result = backtracking("", 0, L);
        for (String password : result) {
            System.out.println(password);
        }
    }

    private static List<String> backtracking(String cur, int lastIdx, int L) {
        List<String> result = new ArrayList<>();
        // 암호 미완성
        if (cur.length() != L) {
            if (cur.length() == 0) {
                for (int i = 0; i < alphabets.length; i++) {
                    result.addAll(backtracking(alphabets[i], i, L));
                }
            }
            else {
                for (int i = lastIdx + 1; i < alphabets.length; i++) {
                    String next = cur + alphabets[i];
                    result.addAll(backtracking(next, i, L));
                }
            }
        }
        // 암호 완성
        else {
            List<String> moeumList = List.of("a", "e", "i", "o", "u");
            int moeumCount = 0;
            for (int i = 0; i < cur.length(); i++) {
                Character c = cur.charAt(i);
                if (moeumList.contains(c.toString())) {
                    moeumCount += 1;
                }
            }
            // 조건을 만족하는 경우
            if (moeumCount >= 1 && L - moeumCount >= 2) {
                return List.of(cur);
            }
            else {
                return List.of();  // emptyList
            }
        }

        return result;
    }
}