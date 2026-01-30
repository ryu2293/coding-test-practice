import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer;
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        int cnt = 1;
        
        for(int i=1; i<words.length; i++) {
            if(i % n == 0) cnt++;
            
            if(words[i].charAt(0) != words[i-1].charAt(words[i-1].length() - 1)) {
                return new int[]{i%n+1, cnt};
            }
            
            if(set.contains(words[i])) {

                return new int[]{i%n+1, cnt};
            }
            set.add(words[i]);
            
        }
        
        return new int[]{0, 0};
    }
}