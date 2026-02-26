import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : topping) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        for(int i=0; i<topping.length; i++) {
            set.add(topping[i]);
            map.put(topping[i], map.getOrDefault(topping[i], 0) - 1);
            if(map.get(topping[i]) == 0) {
                map.remove(topping[i]);
            }

            if(set.size() == map.size()) answer++;
        }


        return answer;
    }
}