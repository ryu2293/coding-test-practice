import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        int size = s.length();
        for(int i=0; i<size; i++) {
            int lt1 = i-1;
            int rt1 = i+1;
            boolean success1 = false;
            while(check(lt1, rt1, size)) {
                if(s.charAt(lt1) == s.charAt(rt1)) {
                    lt1--;
                    rt1++;
                    success1 = true;
                }
                else {
                    break;
                }
            }
            int result1 = 0;
            if(success1) result1 = (rt1 - 1) - (lt1 + 1) + 1;
            else result1 = 1;

            int lt2 = i;
            int rt2 = i+1;
            boolean success2 = false;
            while(check(lt2, rt2, size)) {
                if(s.charAt(lt2) == s.charAt(rt2)) {
                    lt2--;
                    rt2++;
                    success2 = true;
                }
                else {
                    break;
                }
            }
            int result2 = 0;
            if(success2) result2 = (rt2 - 1) - (lt2 + 1) + 1 ;

            int result = Math.max(result1, result2);
            answer = Math.max(answer, result);
        }

        return answer;
    }

    public boolean check(int lt, int rt, int n) {
        if(lt < 0 || rt >= n) return false;
        return true;
    }
}