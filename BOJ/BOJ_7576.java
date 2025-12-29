import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;
    int day;

    public Point(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int m;
    static int n;
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        q = new LinkedList<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    q.offer(new Point(i, j, 0));
                }
            }
        }

        System.out.println(check(arr, bfs(arr)));

    }

    static int check(int[][] arr, int answer) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) return -1;
            }
        }
        return answer;
    }

    static int bfs(int[][] arr) {
        int maxDay = 0;

        while (!q.isEmpty()) {
            Point now = q.poll();
            maxDay = Math.max(maxDay, now.day);

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(arr[nx][ny] == 0) {
                    arr[nx][ny] = 1;
                    q.offer(new Point(nx, ny, now.day+1));
                }
            }
        }
        return maxDay;
    }
}