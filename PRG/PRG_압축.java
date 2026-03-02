import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int num = 1;
        for(char c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), num++);
        }

        for(int i = 0; i < msg.length(); ) {
            String w = "";
            int nextIdx = i;

            while(nextIdx < msg.length() && map.containsKey(msg.substring(i, nextIdx + 1))) {
                w = msg.substring(i, nextIdx + 1);
                nextIdx++;
            }

            answer.add(map.get(w));

            if(nextIdx < msg.length()) {
                String c = msg.substring(i, nextIdx + 1);
                map.put(c, num++);
            }
            i += w.length();
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}