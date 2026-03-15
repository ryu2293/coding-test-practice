import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        int n = tickets.length;

        boolean[] visit = new boolean[n];
        ArrayList<String> al = new ArrayList<>();
        al.add("ICN");

        dfs(0, n, "ICN", tickets, visit, al);

        return al.stream().toArray(String[]::new);
    }

    public boolean dfs(int cnt, int n, String now, String[][] tickets,
                       boolean[] visit, ArrayList<String> al) {
        if (cnt == n) return true;

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            if (tickets[i][0].equals(now)) {
                visit[i] = true;
                al.add(tickets[i][1]);
                if (dfs(cnt+1, n, tickets[i][1], tickets, visit, al)) return true;
                // 백트래킹
                visit[i] = false;
                al.remove(al.size()-1);
            }
        }
        return false;
    }
}