import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;
    int cnt;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Edge{
    int end;
    int weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class Main{
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int h, w;
        while (true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0) return;
            char[][] room = new char[h][w];
            boolean flag = false;
            int idx = 1;
            Point[] edge = new Point[11];

            for(int i=0; i<h; i++){
                String line = br.readLine();
                for(int j=0; j<w; j++){
                    room[i][j] = line.charAt(j);
                    if(room[i][j] == 'o'){
                        edge[0] = new Point(i, j);
                    }
                    else if(room[i][j] == '*'){
                        edge[idx++] = new Point(i, j);
                    }
                }
            }

            // 인접리스트 생성
            ArrayList<ArrayList<Edge>> al = new ArrayList<>();
            for(int i=0; i<idx; i++){
                al.add(new ArrayList<>());
            }
            for(int i=0; i<idx; i++){
                for(int j=i+1; j<idx; j++){
                    int weight = BFS(edge[i], edge[j], h, w, room);
                    // 도착 못 한다면 중단 후 -1 출력
                    if(weight == -1) {
                        flag = true;
                        break;
                    }
                    al.get(i).add(new Edge(j, weight));
                    al.get(j).add(new Edge(i, weight));
                }
            }
            if(flag){
                System.out.println(-1);
                continue;
            }

            int[] ch = new int[idx];
            ch[0] = 1;
            answer=Integer.MAX_VALUE;
            permutation(0, idx, ch, 0, al, 0);
            System.out.println(answer);

        }
    }

    // 1->2->3->4 등 순열 구하기
    static void permutation(int L, int size, int[] ch, int sum, ArrayList<ArrayList<Edge>> al, int start){
        if(L == size-1){
            answer = Math.min(answer, sum);
        }
        else{
            for(Edge e : al.get(start)){
                if(ch[e.end] == 0){
                    ch[e.end] = 1;
                    permutation(L+1, size, ch, sum+e.weight, al, e.end);
                    ch[e.end] = 0;
                }
            }
        }
    }

    // 로봇 청소기 및 먼지들의 최단 경로
    static int BFS(Point start, Point end, int h, int w, char[][] room){
        Queue<Point> q = new LinkedList<>();
        int[][] visited = new int[h][w];
        q.offer(new Point(start.x, start.y, 0));
        visited[start.x][start.y] = 1;
        while (!q.isEmpty()){
            Point now = q.poll();
            if(now.x == end.x && now.y == end.y){
                return now.cnt;
            }
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx<0 || nx>=h || ny<0 || ny>=w) continue;
                if(visited[nx][ny] == 0 && room[nx][ny] != 'x'){
                    visited[nx][ny] = 1;
                    q.offer(new Point(nx, ny, now.cnt+1));
                }
            }
        }
        return -1;
    }
}