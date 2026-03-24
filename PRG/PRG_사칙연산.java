import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int answer = -1;

        int n = arr.length / 2 + 1;
        ArrayList<Integer> num = new ArrayList<>();
        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];

        for(int i=0; i<arr.length; i += 2) {
            num.add(Integer.parseInt(arr[i]));
        }

        for(int i=0; i<n; i++) {
            int number = num.get(i);
            maxDp[i][i] = number;
            minDp[i][i] = number;
        }

        for(int len = 1; len < n; len++) {
            for(int i = 0; i < n - len; i++) {
                int j = i + len;
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++) {
                    String op = arr[k * 2 + 1];
                    int leftMax = maxDp[i][k];
                    int leftMin = minDp[i][k];
                    int rightMax = maxDp[k+1][j];
                    int rightMin = minDp[k+1][j];

                    if(op.equals("+")) {
                        maxDp[i][j] = Math.max(maxDp[i][j], leftMax + rightMax);
                        minDp[i][j] = Math.min(minDp[i][j], leftMin + rightMin);
                    }
                    else {
                        maxDp[i][j] = Math.max(maxDp[i][j], leftMax - rightMin);
                        minDp[i][j] = Math.min(minDp[i][j], leftMin - rightMax);
                    }
                }
            }
        }

        return maxDp[0][n-1];
    }
}