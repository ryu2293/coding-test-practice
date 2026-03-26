import java.util.*;

class Point {
    int x;
    int y;
    int cost;
    int dir; // 방향

    public Point(int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.dir = dir;
    }
}

class Solution {
    int n;
    int answer;

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][][] visit;

    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;

        n = board.length;
        visit = new int[n][n][4];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<4; k++) {
                    visit[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }


        bfs(board);

        return answer;
    }

    public void bfs(int[][] board) {
        Queue<Point> q = new LinkedList<>();
        if(board[0][1] == 0) q.offer(new Point(0, 1, 100, 3));
        if(board[1][0] == 0) q.offer(new Point(1, 0, 100, 2));
        visit[0][1][3] = 100;
        visit[1][0][2] = 100;

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.x == n-1 && p.y == n-1) {
                answer = Math.min(answer, p.cost);
            }
            // 상, 좌, 하, 우
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(board[nx][ny] == 1) continue;

                int diff = Math.abs(p.dir - i);
                int cost = 0;

                if(diff == 1 || diff == 3) {
                    cost = p.cost + 600;
                    if(visit[nx][ny][i] <= cost) continue;

                    q.offer(new Point(nx, ny, cost, i));
                }
                else if (diff == 0) {
                    cost = p.cost + 100;
                    if(visit[nx][ny][i] <= cost) continue;

                    q.offer(new Point(nx, ny, cost, i));
                }
                else {
                    continue;
                }

                visit[nx][ny][i] = cost;
            }
        }
    }
}