import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 40 50 50 60 70
        Arrays.sort(people);

        int lt = 0, rt = people.length-1;
        while(lt <= rt) {
            int diff = limit - people[rt--];
            if(diff >= people[lt]) {
                lt++;
            }
            answer++;
        }

        return answer;
    }
}