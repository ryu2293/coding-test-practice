import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String num = Integer.toString(n, k);

        int lt = -1;
        int rt = 0;

        while(rt <= num.length()){
            if(rt == num.length()){
                String temp = num.substring(lt+1, rt);
                if(temp.isEmpty() || temp == null) continue;
                long sub = Long.parseLong(temp);
                if(prime(sub)) answer++;
                break;
            }

            if(num.charAt(rt) == '0') {
                String temp = num.substring(lt+1, rt);
                if(temp.isEmpty()){
                    rt++;
                    continue;
                }
                long sub = Long.parseLong(temp);
                if(prime(sub)) answer++;
                lt = rt;
            }
            rt++;
        }

        return answer;
    }

    public boolean prime(long sub) {
        if(sub < 2) return false;
        for(int i=2; i <= Math.sqrt(sub); i++) {
            if(sub % i == 0) return false;
        }
        return true;
    }
}