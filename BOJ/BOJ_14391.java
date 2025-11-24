import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        int answer = 0;
        for (int c = 0; c < (1 << N * M); c++) {
            int sum = 0;

            for (int i = 0; i < N; i++) {
                int cur = 0;
                for (int j = 0; j < M; j++) {
                    int pos = i * M + j;

                    if ((c & 1 << pos) == 0) {
                        cur = cur * 10 + arr[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            for (int j = 0; j < M; j++) {
                int cur = 0;
                for (int i = 0; i < N; i++) {
                    int pos = i * M + j;

                    if ((c & 1 << pos) != 0) {
                        cur = cur * 10 + arr[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }


}