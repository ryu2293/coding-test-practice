import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int cnt=0; cnt<s.length; cnt++) {
            String line = s[cnt];
            StringBuilder sb = new StringBuilder();
            int k=0;

            for(char c : line.toCharArray()) {
                sb.append(c);

                int size = sb.length();
                if(size >= 3 && sb.substring(size-3).equals("110")) {
                    sb.delete(size-3, size);
                    k++;
                }
            }

            StringBuilder temp = new StringBuilder();
            for(int i=0; i<k; i++) {
                temp.append("110");
            }
            int len = sb.length();

            // 11 앞에 넣기, 1 앞에 넣기, 그냥 뒤에 붙이기
            int point = len;
            boolean flag = false;
            for(int i=0; i<len-1; i++) {
                if(sb.charAt(i) == '1' && sb.charAt(i+1) == '1') {
                    flag = true;
                    point = i;
                    break;
                }
            }

            if(!flag) {
                for(int i=0; i < len; i++) {
                    if(i == len-1 && sb.charAt(i) == '1') {
                        point = i;
                        break;
                    }
                    if(sb.charAt(i) == '1' && sb.charAt(i+1) != '0') {
                        point = i;
                        break;
                    }
                }
            }
            sb.insert(point, temp);
            answer[cnt] = sb.toString();
        }

        return answer;
    }
}