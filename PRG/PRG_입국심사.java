import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        long minTime = 1;
        long maxTime = 0;
        for(int time : times) {
            maxTime = Math.max(maxTime, (long)time);
        }
        maxTime *= n;


        while(minTime <= maxTime) {
            long mid = (minTime + maxTime)/2;
            System.out.println(mid);
            long sum = 0;

            for(int time : times) {
                sum += mid / time;
                if(sum >= n) break;
            }

            if(sum < n) {
                minTime = mid + 1;
            }
            else {
                answer = mid;
                maxTime = mid - 1;
            }
        }

        return answer;
    }
}