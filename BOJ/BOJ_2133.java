import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        if(n % 2 != 0) System.out.println(0);
        else if(n == 2) System.out.println(3);
        else if(n == 4) System.out.println(11);
        else {
            int[] dp = new int[n+1];
            dp[2] = 3; dp[4] = 11;

            for(int i=6; i<=n; i+=2) {
                dp[i] = (dp[i-2] * 3) + 2;
                for(int j=i-4; j>=2; j-=2) {
                    dp[i] += dp[j] * 2;
                }
            }
            System.out.println(dp[n]);
        }
    }
}