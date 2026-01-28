import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];

        if(n <= 1) {
            System.out.println(dp[1]);
            return;
        }

        dp[2] = dp[1] + arr[2];

        if(n <= 2) {
            System.out.println(dp[n]);
            return;
        }
        for(int i=3; i<=n; i++) {
            int cost = arr[i];

            dp[i] = cost + Math.max(dp[i-2], arr[i-1] + dp[i-3]);
        }
        System.out.println(dp[n]);
    }
}