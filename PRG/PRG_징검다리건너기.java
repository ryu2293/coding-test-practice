import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        // 건널 수 있는 사람이 n명인가? 확인 후 불가능하면 n을 감소, 가능하다면 n을 증가시켜봄.
        // 확인하는 방법은 stones[i] - n이 0 이하인지 판단 후 k 이상 연속되는지 확인해봄.

        int n = stones.length;
        int lt = 0, rt = 200000000;
        while(lt <= rt) {
            int mid = (lt + rt) / 2;

            boolean flag = false;
            int dist = 0;
            for(int i=0; i<n; i++) {
                if(stones[i] - mid + 1 <= 0) {
                    dist++;
                    if(dist >= k) {
                        flag = true;
                        break;
                    }
                } else {
                    dist = 0;
                }
            }

            if(flag) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
                answer = Math.max(answer, mid);
            }
        }


        return answer;
    }
}