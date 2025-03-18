import java.io.*;
import java.util.*;


public class BOJ10830 {

    private static int N;
    private static long[][] matrix;

    private static long[][] multiply(long[][] A, long[][] B) {
        long[][] result = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += ((A[i][k] * B[k][j]) % 1000);
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }

    private static long[][] divideConquer(long exp) {
        if (exp == 1) {
            return matrix;
        }

        long[][] half = divideConquer(exp / 2);
        long[][] result = multiply(half, half);

        if (exp % 2 == 1) {
            result = multiply(matrix, result);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // N*N 행렬
        long B = Long.parseLong(st.nextToken());  // B 제곱

        matrix = new long[N][N];
        for (int i = 0; i <N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long[][] result = new long[N][N];
        result = divideConquer(B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", result[i][j] % 1000);
            }
            System.out.println();
        }
    }
}
