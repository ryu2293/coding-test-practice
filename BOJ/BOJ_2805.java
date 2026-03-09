import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());
        long lt = 0;
        long rt = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            rt = Math.max(rt, trees[i]);
        }

        long mid = 0;
        long answer = 0;
        while (lt <= rt) {
            long sum = 0;
            mid = (lt + rt) / 2;
            for (long x : trees) {
                long result = x - mid;
                if (result < 0) continue;
                sum += result;
            }

            if (sum < m) {
                rt = mid - 1;
            } else  {
                lt = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }
}