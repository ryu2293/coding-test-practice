import java.util.*;

class Solution {
    int answer = 0;
    boolean stop = false;
    
    public int solution(int k, int[][] dungeons) {

        boolean[] visit = new boolean[dungeons.length];
        dfs(k, dungeons, visit, 0);
        
        return answer;
    }
    
    public void dfs(int k, int[][] dungeons, boolean[] visit, int depth) {
        if(stop) return;
        
        if(depth == dungeons.length) {
            stop = true;
            return;
        }
        else {
            
            for(int i=0; i<dungeons.length; i++){
                if(visit[i]) continue;
                
                if(k < dungeons[i][0]) {
                    continue;
                }
                visit[i] = true;
                answer = Math.max(answer, depth+1);
                dfs(k-dungeons[i][1], dungeons, visit, depth+1);
                visit[i] = false;
            }
        }
    }
}