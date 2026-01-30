import java.util.*;

class Solution {
    public int[] solution(String s) {

        int delete = 0;
        int cnt = 0;
        
        while(true) {
            cnt++;
            int n = s.length();

            int zeroCnt = 0;            
            for(int i=0; i<n; i++) {
                if(s.charAt(i) == '0') {
                    zeroCnt++;
                }       
            }
            delete += zeroCnt;
            
            s = Integer.toBinaryString(n - zeroCnt);
            
            if(s.equals("1")) break;
        }
        
        int[] answer = {cnt, delete};
        return answer;
    }
}