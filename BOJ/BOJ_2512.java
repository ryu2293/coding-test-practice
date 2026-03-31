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
        int[] moneys = new int[n];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for(int i=0; i<n; i++) {
            moneys[i] = Integer.parseInt(st.nextToken());
            sum += moneys[i];
        }
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        Arrays.sort(moneys);
        if(sum < m) {
            System.out.println(moneys[n-1]);
            return;
        }

        int lt = 1; int rt = moneys[n-1];
        int answer = 0;
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            sum = 0;

            for(int x : moneys) {
                if(x > mid) {
                    sum += mid;
                }
                else {
                    sum += x;
                }
            }

            if(sum > m) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }


}