import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        int kind = (int)Arrays.stream(gems).distinct().count();

        int lt = 0; int rt = 1;
        int n = gems.length;
        int start = 0; int end = 1;
        int dist = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);

        while(lt <= rt) {

            if(map.size() != kind) {
                if(rt >= n) break;
                map.put(gems[rt], map.getOrDefault(gems[rt], 0) + 1);
                rt++;
            } else {
                if(dist > rt - lt) {
                    dist = rt - lt;
                    start = lt;
                    end = rt;
                }

                map.put(gems[lt], map.get(gems[lt]) - 1);
                if(map.get(gems[lt]) == 0) map.remove(gems[lt]);
                lt++;
            }
        }

        return new int[]{start+1, end};
    }
}