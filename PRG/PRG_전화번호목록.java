import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);
        Set<String> set = new HashSet<>();

        for(String num : phone_book) {
            StringBuilder st = new StringBuilder();
            for(int i=0; i<num.length(); i++) {
                st.append(num.charAt(i));
                if(set.contains(st.toString())) {
                    System.out.println(st);
                    return false;
                }
            }
            set.add(num);
        }

        return answer;
    }
}