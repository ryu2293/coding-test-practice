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
static int r, c, answer=0;
static boolean flag = false;
static char[][] arr;
static int[][] visited;
static int[] dx = {0, -1, 0, 1};
static int[] dy = {-1, 0, 1, 0};
static Queue water = new LinkedList<>();
static Queue q = new LinkedList<>();
public static void main(String[] args) throws IOException {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());
r = Integer.parseInt(st.nextToken());
c = Integer.parseInt(st.nextToken());
arr = new char[r][c];
visited = new int[r][c];
for(int i=0; i<r; i++){
String line = br.readLine();
for(int j=0; j<c; j++){
arr[i][j] = line.charAt(j);
// 물이 차있는 지역
if(arr[i][j] == '*') water.offer(new Point(i,j));
// 고슴도치 위치
if(arr[i][j] == 'S') {
arr[i][j] = '.';
visited[i][j] = 1;
q.offer(new Point(i,j));
}
}
}
BFS();
if(flag) System.out.println(answer+1);
else System.out.println("KAKTUS");
}

static void BFS(){
    while (!q.isEmpty()){
        // 물 확장
        int size = water.size();
        for(int i=0; i<size; i++){
            Point nowWater = water.poll();
            for(int j=0; j<4; j++){
                int nx = nowWater.x + dx[j];
                int ny = nowWater.y + dy[j];
                if(nx>=0 && nx<r && ny>=0 && ny<c){
                    if(arr[nx][ny] == '.') {
                        arr[nx][ny] = '*';
                        water.offer(new Point(nx, ny));
                    }
                }
            }
        }

        // 고슴도치 이동
        size = q.size();
        for(int i=0; i<size; i++){
            Point now = q.poll();
            for(int j=0; j<4; j++){
                int nx = now.x + dx[j];
                int ny = now.y + dy[j];
                if(nx>=0 && nx<r && ny>=0 && ny<c){
                    // 고슴도치가 비버의 굴로 도착하면 리턴
                    if(arr[nx][ny] == 'D') {
                        flag = true;
                        return;
                    }
                    if(arr[nx][ny] == '.' && visited[nx][ny] == 0){
                        visited[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }
        answer++;
    }

}
}