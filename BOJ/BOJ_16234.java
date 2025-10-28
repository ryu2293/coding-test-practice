import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main{
    static int n, l, r, answer = 0;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            boolean[][] visited = new boolean[n][n];
            boolean move = false;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(visited[i][j]) continue;

                    visited[i][j] = true;
                    ArrayList<Point> al = new ArrayList<>();
                    Queue<Point> q = new LinkedList<>();

                    al.add(new Point(i, j));
                    q.offer(new Point(i, j));
                    while (!q.isEmpty()){
                        Point now = q.poll();
                        for(int d=0; d<4; d++){
                            int nx = now.x + dx[d];
                            int ny = now.y + dy[d];

                            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                            if(visited[nx][ny]) continue;
                            int diff = Math.abs(arr[now.x][now.y] - arr[nx][ny]);
                            if(diff < l || diff > r) continue;

                            visited[nx][ny] = true;
                            al.add(new Point(nx, ny));
                            q.offer(new Point(nx, ny));
                        }
                    }

                    if(al.size()>1){
                        int sum = 0;
                        for(Point p : al){
                            sum += arr[p.x][p.y];
                        }

                        int result = sum / al.size();
                        for(Point p : al){
                            arr[p.x][p.y] = result;
                        }
                        move = true;
                    }
                }
            }
            if(!move){
                System.out.println(answer);
                break;
            }
            answer++;
        }
    }
}
