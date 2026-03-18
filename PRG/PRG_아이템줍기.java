import java.util.*;

class Point {
    int x;
    int y;
    int dist;

    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Solution {
    int[][] map = new int[101][101];
    boolean[][] visit = new boolean[101][101];
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int answer = 0;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        int n = rectangle.length;
        for(int i = 0; i < n; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            for(int p = x1; p <= x2; p++) {
                for(int q = y1; q <= y2; q++) {
                    if(p == x1 || p == x2 || q == y1 || q == y2) {
                        if(map[p][q] == 0) {
                            map[p][q] = 1;
                        }
                    } else {
                        map[p][q] = 2;
                    }
                }
            }
        }

        bfs(characterX * 2, characterY*2, itemX*2, itemY*2);

        return answer;
    }

    public void bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(characterX, characterY, 0));
        visit[characterX][characterY] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.x == itemX && p.y == itemY) {
                answer = p.dist / 2;
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.dist + 1));
                }
            }
        }
    }
}