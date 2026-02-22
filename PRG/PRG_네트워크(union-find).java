import java.util.*;

class Solution {

    static int[] unf;

    public int find(int v) {
        if(unf[v] == v) return v;
        else return unf[v] = find(unf[v]);
    }

    public void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if(x != y) unf[x] = y;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        unf = new int[n];
        for(int i=0; i<n; i++) {
            unf[i] = i;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        for(int i=0; i<n; i++) {
            if(i == unf[i]) answer++;
        }

        return answer;
    }
}