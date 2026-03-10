import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        int[] arr = new int[rocks.length + 1];
        for(int i=0; i<rocks.length; i++) {
            arr[i] = rocks[i];
        }
        arr[rocks.length] = distance;

        Arrays.sort(arr);

        int lt = 1;
        int rt = distance;
        while(lt <= rt) {
            int mid = (lt + rt) / 2;
            int before = 0;
            int cnt = 0;
            for(int i=0; i<arr.length; i++) {
                int sub = arr[i] - before;
                if(sub < mid) {
                    cnt++;
                } else {
                    before = arr[i];
                }
            }
            // 2 11 14 17 21 25
            if(cnt <= n) {
                lt = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                rt = mid - 1;
            }
        }

        return answer;
    }
}