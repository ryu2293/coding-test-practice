import java.util.*;

class Solution {

    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visit = new boolean[n];

        for(int i=0; i<n; i++) {
            if(!visit[i]) {
                dfs(computers, visit, i, n);
                answer++;
            }
        }

        return answer;
    }

    public void dfs(int[][] computers, boolean[] visit, int now, int n) {
        visit[now] = true;

        for(int i=0; i<n; i++) {
            if(i == now) continue;
            if(visit[i]) continue;
            if(computers[now][i] == 1) {
                dfs(computers, visit, i, n);
            }
        }
    }
}