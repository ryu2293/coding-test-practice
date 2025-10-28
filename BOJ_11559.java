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
  static char[][] arr;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, -1, 0, 1};
  static int r = 12, c = 6, answer = 0;
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      arr = new char[r][c];
      for(int i=0; i < r; i++){
          String line = br.readLine();
          for(int j=0; j<c; j++){
              arr[i][j] = line.charAt(j);
          }
      }

      int cnt = -1;
      while (cnt != answer){
          cnt = answer;
          search();
      }

      System.out.println(answer);

  }

  static void move(){
      Queue<Character> li = new LinkedList<>();
      for(int col = 0; col < c; col++){
          for(int row = r-1; row>=0; row--){
              if(arr[row][col] != '.'){
                  li.offer(arr[row][col]);
              }
          }
          int x = r-1;
          while (!li.isEmpty()){
              arr[x][col] = li.poll();
              x--;
          }
          while (x >= 0){
              arr[x][col] = '.';
              x--;
          }
      }
  }

  static boolean BFS(int x, int y, boolean[][] visited){
      char target = arr[x][y];
      Queue<Point> q= new LinkedList<>();
      List<Point> li = new ArrayList<>();
      q.offer(new Point(x, y));
      li.add(new Point(x, y));
      while (!q.isEmpty()){
          Point now = q.poll();
          for(int d=0; d<4; d++){
              int nx = now.x + dx[d];
              int ny = now.y + dy[d];
              if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
              if(visited[nx][ny]) continue;
              if(arr[nx][ny] == target){
                  visited[nx][ny] = true;
                  q.offer(new Point(nx, ny));
                  li.add(new Point(nx, ny));
              }
          }
      }
      if(li.size() >= 4){
          for(Point p : li){
              arr[p.x][p.y] = '.';
          }
          return true;
      }
      return false;
  }

  static void search(){
      boolean[][] visited = new boolean[r][c];
      boolean flag = false;
      for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
              if(arr[i][j] != '.' && !visited[i][j]){
                  visited[i][j] = true;
                  if(BFS(i, j, visited)) flag = true;
              }
          }
      }
      if(flag){
          answer++;
          move();
      }
  }
}