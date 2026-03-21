import java.util.*;

class Point {
    int x1, y1, x2, y2, dist, dir;
    public Point(int x1, int y1, int x2, int y2, int dist, int dir) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.dist = dist; this.dir = dir;
    }
}

class Solution {
    int n;
    int answer = 0;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int solution(int[][] board) {
        n = board.length;
        bfs(board);
        return answer;
    }

    public void bfs(int[][] board) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, 1, 0, 0));
        boolean[][][][] visit = new boolean[n][n][n][n];
        visit[0][0][0][1] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if ((p.x1 == n-1 && p.y1 == n-1) || (p.x2 == n-1 && p.y2 == n-1)) {
                answer = p.dist;
                return;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx1 = p.x1 + dx[i];
                int ny1 = p.y1 + dy[i];
                int nx2 = p.x2 + dx[i];
                int ny2 = p.y2 + dy[i];

                if (!check(nx1, ny1, nx2, ny2)) continue;
                if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;

                // 정규화
                if (nx1 > nx2 || (nx1 == nx2 && ny1 > ny2)) {
                    int tx = nx1; int ty = ny1;
                    nx1 = nx2; ny1 = ny2;
                    nx2 = tx; ny2 = ty;
                }

                if (visit[nx1][ny1][nx2][ny2]) continue;
                visit[nx1][ny1][nx2][ny2] = true;
                q.offer(new Point(nx1, ny1, nx2, ny2, p.dist+1, p.dir));
            }

            // 회전
            if (p.dir == 0) rotateHorizontal(p, q, visit, board);
            else rotateVertical(p, q, visit, board);
        }
    }

    // 가로 상태 (x1 == x2): 위아래로 회전
    public void rotateHorizontal(Point p, Queue<Point> q, boolean[][][][] visit, int[][] board) {
        int[] hor = {-1, 1};

        for (int i = 0; i < 2; i++) {
            int nx = p.x1 + hor[i];

            // 회전하려면 양쪽 칸 모두 비어있어야 함
            if (nx >= 0 && nx < n && board[nx][p.y1] == 0 && board[nx][p.y2] == 0) {
                // y1 기준 회전 결과
                int rx1 = Math.min(p.x1, nx);
                int rx2 = Math.max(p.x1, nx);
                if (!visit[rx1][p.y1][rx2][p.y1]) {
                    visit[rx1][p.y1][rx2][p.y1] = true;
                    q.offer(new Point(rx1, p.y1, rx2, p.y1, p.dist+1, 1));
                }

                // y2 기준 회전 결과
                rx1 = Math.min(p.x2, nx);
                rx2 = Math.max(p.x2, nx);
                if (!visit[rx1][p.y2][rx2][p.y2]) {
                    visit[rx1][p.y2][rx2][p.y2] = true;
                    q.offer(new Point(rx1, p.y2, rx2, p.y2, p.dist+1, 1));
                }
            }
        }
    }

    // 세로 상태 (y1 == y2): 좌우로 회전
    public void rotateVertical(Point p, Queue<Point> q, boolean[][][][] visit, int[][] board) {
        int[] ver = {-1, 1};

        for (int i = 0; i < 2; i++) {
            int ny = p.y1 + ver[i];

            // 회전하려면 양쪽 칸 모두 비어있어야 함
            if (ny >= 0 && ny < n && board[p.x1][ny] == 0 && board[p.x2][ny] == 0) {
                // x1 기준 회전 결과
                int ry1 = Math.min(p.y1, ny);
                int ry2 = Math.max(p.y1, ny);
                if (!visit[p.x1][ry1][p.x1][ry2]) {
                    visit[p.x1][ry1][p.x1][ry2] = true;
                    q.offer(new Point(p.x1, ry1, p.x1, ry2, p.dist+1, 0));
                }

                // x2 기준 회전 결과
                ry1 = Math.min(p.y2, ny);
                ry2 = Math.max(p.y2, ny);
                if (!visit[p.x2][ry1][p.x2][ry2]) {
                    visit[p.x2][ry1][p.x2][ry2] = true;
                    q.offer(new Point(p.x2, ry1, p.x2, ry2, p.dist+1, 0));
                }
            }
        }
    }

    public boolean check(int nx1, int ny1, int nx2, int ny2) {
        if (nx1 < 0 || ny1 < 0 || nx2 < 0 || ny2 < 0) return false;
        if (nx1 >= n || ny1 >= n || nx2 >= n || ny2 >= n) return false;
        return true;
    }
}
