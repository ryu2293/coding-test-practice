import java.util.*;

class Solution {
    static int answer;

    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;

        boolean[] visit = new boolean[words.length];
        dfs(0, words.length, begin, target, visit, words);
        if(answer == Integer.MAX_VALUE) return 0;

        return answer;
    }

    public void dfs(int now, int n, String begin, String target, boolean[] visit, String[] words) {

        if(begin.equals(target)) {
            answer = Math.min(answer, now);
            return;
        }
        else {
            for(int i=0; i<n; i++) {
                if(visit[i]) continue;

                String word = words[i];
                int cnt = 0;

                for(int j=0; j<begin.length(); j++) {
                    if(begin.charAt(j) != word.charAt(j)) {
                        cnt++;
                    }
                }

                if(cnt != 1) continue;

                visit[i] = true;
                dfs(now+1, n, word, target, visit, words);
                visit[i] = false;
            }
        }
    }
}