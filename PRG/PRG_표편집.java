import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        int[] prev = new int[n+2];
        int[] next = new int[n+2];

        for(int i=1; i<=n; i++) {
            prev[i] = i-1;
            next[i] = i+1;
        }
        next[0] = 1;
        prev[n+1] = n;

        int now = k+1;
        Stack<Integer> stack = new Stack<>();

        for(String line : cmd) {
            StringTokenizer st = new StringTokenizer(line);
            String token = st.nextToken();

            if(token.equals("D")) {
                int num = Integer.parseInt(st.nextToken());
                for(int i=0; i<num; i++) now = next[now];
            }
            else if(token.equals("U")) {
                int num = Integer.parseInt(st.nextToken());
                for(int i=0; i<num; i++) now = prev[now];
            }
            else if(token.equals("C")) {
                stack.push(now);
                int prevNow = prev[now];
                int nextNow = next[now];
                next[prevNow] = nextNow;
                prev[nextNow] = prevNow;
                now = (nextNow == n+1) ? prevNow : nextNow;
            }
            else {
                int num = stack.pop();
                next[prev[num]] = num;
                prev[next[num]] = num;
            }
        }

        boolean[] check = new boolean[n+1];
        while(!stack.isEmpty()) {
            check[stack.pop()] = true;
        }

        for(int i=1; i<=n; i++) {
            answer.append(check[i] ? "X" : "O");
        }

        return answer.toString();
    }
}