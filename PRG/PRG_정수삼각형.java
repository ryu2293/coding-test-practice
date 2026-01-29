import java.util.*;

// dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);

public class Solution {
    public static int solution(int[][] triangle){
        int answer = 0;
        int n = triangle.length; // size
        int[][] dp = new int[n][];
        
        for(int i=0; i<n; i++) {
            dp[i] = new int[i+1];
        }
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                if(j == 0) {
                    dp[i][j] = triangle[i][j] + dp[i-1][j];
                }
                else if(j == triangle[i].length - 1) {
                    dp[i][j] = triangle[i][j] + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);   
                }
            }
        }
        
        for(int maxVal : dp[n-1]) {
            answer = Math.max(answer, maxVal);    
        }
        
        return answer;
    }
}