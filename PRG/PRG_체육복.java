import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;

        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        // 1 2 3 4 5
        // 2 0 2 0 2
        for(int idx : lost) {
            arr[idx] -= 1;
        }
        for(int idx : reserve) {
            arr[idx] += 1;
        }

        for(int i=1; i<=n; i++) {
            if(arr[i] == 0) {
                if(i-1 >= 1 && arr[i-1] == 2) {
                    arr[i]--;
                    arr[i-1]++;
                }
                else if(i+1 <= n && arr[i+1] == 2) {
                    arr[i]--;
                    arr[i+1]++;
                }
                else answer--;
            }
        }


        return answer;
    }
}