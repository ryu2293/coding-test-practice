import java.util.*;

class Point {
    int x;
    int y;
    int time;

    public Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int bfs(int sx, int sy, char target, char[][] maps) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sx, sy, 0));
        boolean[][] visit = new boolean[maps.length][maps[0].length];
        visit[sx][sy] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(maps[p.x][p.y] == target) {
                return p.time;
            }
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length) continue;
                if(visit[nx][ny] || maps[nx][ny] == 'X') continue;

                visit[nx][ny] = true;
                q.offer(new Point(nx, ny, p.time + 1));
            }
        }
        return -1;
    }

    public int solution(String[] maps) {
        int answer = 0;

        char[][] arr = new char[maps.length][maps[0].length()];
        int sx=0, sy=0, lx=0, ly=0;
        for(int i = 0; i < maps.length; i++) {
            String line = maps[i];
            for(int j=0; j < line.length(); j++) {
                arr[i][j] = line.charAt(j);
                if(arr[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if(arr[i][j] == 'L') {
                    lx = i;
                    ly = j;
                }
            }
        }



        int result = bfs(sx, sy, 'L', arr);
        System.out.println(result);
        if(result == -1) return -1;
        answer += result;

        result = bfs(lx, ly, 'E', arr);
        System.out.println(result);
        if(result == -1) return -1;
        answer += result;

        return answer;
    }
}