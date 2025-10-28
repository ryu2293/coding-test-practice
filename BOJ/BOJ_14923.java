import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;
    int time;
    int magic;

    public Point(int x, int y, int time, int magic) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.magic = magic;
    }
}

public class Main{
    static int n, m, Hx, Hy, Ex, Ey, answer=Integer.MAX_VALUE;
    static int[][] arr;
    static int[][][] visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Hx  = Integer.parseInt(st.nextToken());
        Hy = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());
        arr = new int[n+1][m+1];
        visited = new int[n+1][m+1][2];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        BFS();
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void BFS(){
        Queue<Point> q = new LinkedList<>();
        visited[Hx][Hy][0] = 1;
        q.offer(new Point(Hx, Hy, 0, 0));
        while (!q.isEmpty()){
            Point now = q.poll();
            if(now.x == Ex && now.y == Ey) answer = Math.min(answer, now.time);
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx>=1 && nx<=n && ny>=1 && ny <=m){
                    // 다음 칸이 빈 칸일 때
                    if(arr[nx][ny] == 0){
                        if(visited[nx][ny][now.magic] == 0){
                            visited[nx][ny][now.magic] = 1;
                            q.offer(new Point(nx, ny, now.time+1, now.magic));
                        }
                    }
                    // 다음 칸이 벽일 때
                    else{
                        // 마법 지팡이를 1회 사용할 때
                        if(visited[nx][ny][1] == 0 && now.magic==0){
                            visited[nx][ny][1] = 1;
                            q.offer(new Point(nx, ny, now.time+1, 1));
                        }
                    }

                }
            }
        }
    }
}