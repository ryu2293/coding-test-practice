import java.util.*;

class Point {
    int now, cnt;
    public Point(int now, int cnt) {
        this.now = now; this.cnt = cnt;
    }
}

class Solution {
    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }

    public int bfs(int x, int y, int n) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, 0));
        boolean[] visit = new boolean[y+1];
        visit[x] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.now == y) return p.cnt;

            int[] next = {p.now + n, p.now * 2, p.now * 3};
            for (int cal : next) {
                if (cal > y || visit[cal]) continue;
                visit[cal] = true;
                q.offer(new Point(cal, p.cnt + 1));
            }
        }
        return -1;
    }
}