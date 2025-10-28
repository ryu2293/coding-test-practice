import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;
    int di;
    public Point(int x, int y, int di) {
        this.x = x;
        this.y = y;
        this.di = di;
    }
}

public class Main{
    static int[][] arr;
    static int n, m, answer = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int di = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS(x, y, di);
        System.out.println(answer);

    }

    static void BFS(int x, int y, int di){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, di));
        while (!q.isEmpty()){
            Point now = q.poll();

            // 현재 칸이 청소되지 않았을 때
            if(arr[now.x][now.y] == 0){
                arr[now.x][now.y] = 2;
                answer++;
            }

            // 인접한 칸 중 청소되지 않은 빈칸이 없을 때
            if(!clean(now.x, now.y)){
                int nd = (now.di+2)%4;
                int nx = now.x + dx[nd];
                int ny = now.y + dy[nd];
                if(arr[nx][ny] == 1) return;
                else q.offer(new Point(nx, ny, now.di));
            }
            // 인접한 칸 중 청소되지 않은 빈칸이 있을 때
            else{
                for(int i=3; i>=0; i--){
                    int nd = (now.di+i)%4;
                    int nx = now.x+dx[nd];
                    int ny = now.y+dy[nd];
                    if(arr[nx][ny] == 0){
                        q.offer(new Point(nx, ny, nd));
                        break;
                    }
                }
            }
        }
    }
    // 인접한 칸을 확인하는 함수
    static boolean clean(int x, int y){
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(arr[nx][ny] == 0) return true;
        }
        return false;
    }
}