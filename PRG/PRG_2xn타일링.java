class Solution {
    public int solution(int n) {
        int answer = 0;
        // dp[0] = 1 dp[1] = 2 dp[2] = 3 dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[n];
        dp[0] = 1; dp[1] = 2;
        if(n <= 2) return dp[n-1];

        for(int i=2; i<n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }

        return dp[n-1];
    }
}