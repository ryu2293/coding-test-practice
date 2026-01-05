import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static ArrayList<Point> al;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) al.add(new Point(i, j));
            }
        }

        comb(arr, 0, 0, n, m);
        System.out.println(answer);

    }

    static int bfs(int[][] arr, int n, int m) {
        int value = 0;
        int[][] tmp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = arr[i][j];
                if(tmp[i][j] == 0) value++;
            }
        }
        Queue<Point> q = new LinkedList<>();
        for (Point p : al) {
            q.offer(new Point(p.x, p.y));
        }

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 2;
                    value--;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        return value;
    }

    static void comb(int[][] arr, int cnt, int start, int n, int m) {
        if (cnt == 3) {
            int value = bfs(arr, n, m);
            answer = Math.max(answer, value);
        } else {
            for (int i = start; i < n * m; i++) {
                int x = i / m;
                int y = i % m;

                if(arr[x][y] == 0) {
                    arr[x][y] = 1;
                    comb(arr, cnt + 1, i + 1, n, m);
                    arr[x][y] = 0;
                }

            }
        }
    }
}