import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;

        char[] arr = new char[name.length()];
        Arrays.fill(arr, 'A');

        for(char c : name.toCharArray()) {
            int min = Math.min(c - 'A', 'Z' - c + 1);
            answer += min;
        }

        int move = name.length() - 1;
        for(int i=0; i<name.length(); i++) {
            int next = i + 1;
            while(next < name.length() && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, i * 2 + name.length() - next);
            move = Math.min(move, (name.length() - next) * 2 + i);
        }

        answer += move;
        return answer;
    }
}