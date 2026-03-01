import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        Map<String, Integer> result = new HashMap<String, Integer>();
        Map<String, Integer> now = new HashMap<String, Integer>();

        for(int i=0; i<want.length; i++) {
            result.put(want[i], number[i]);
        }

        for(int i=0; i<10; i++) {
            now.put(discount[i], now.getOrDefault(discount[i], 0) + 1);
        }


        for(int i=10; i<=discount.length; i++) {
            if(result.equals(now)) {
                answer++;
            }

            if(i == discount.length) break;

            now.put(discount[i], now.getOrDefault(discount[i], 0) + 1);
            now.put(discount[i-10], now.getOrDefault(discount[i-10], 0) - 1);
            if(now.get(discount[i-10]) == 0) now.remove(discount[i-10]);
        }

        return answer;
    }
}