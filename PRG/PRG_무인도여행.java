import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int[] solution(String[] maps) {
        ArrayList<Integer> answer = new ArrayList<>();
        int row = maps.length;
        int col = maps[0].length();
        char[][] arr = new char[row][col];
        boolean[][] visit = new boolean[row][col];

        for(int i=0; i<row; i++) {
            String line = maps[i];
            for(int j=0; j<col; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(!visit[i][j] && arr[i][j] != 'X') {
                    int result = bfs(i, j, arr, visit);
                    answer.add(result);
                }
            }
        }

        if(answer.isEmpty()) return new int[]{-1};
        Collections.sort(answer);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public int bfs(int x, int y, char[][] arr, boolean[][] visit) {
        Queue<Point> q = new LinkedList<>();
        int sum = 0;
        sum += (int)(arr[x][y] - '0');
        q.offer(new Point(x, y));
        visit[x][y] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx<0 || ny<0 || nx >= arr.length || ny >= arr[0].length) continue;
                if(!visit[nx][ny] && arr[nx][ny] != 'X') {
                    q.offer(new Point(nx, ny));
                    sum += (int)(arr[nx][ny] - '0');
                    visit[nx][ny] = true;
                }
            }
        }
        return sum;
    }
}