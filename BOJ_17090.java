import java.io.;
import java.util.;

public class Main{
static char[][] arr;
static int[][] check;
static boolean[][] visited;
static int n, m, answer = 0;

public static void main(String[] args) throws IOException {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());
n = Integer.parseInt(st.nextToken());
m = Integer.parseInt(st.nextToken());
arr = new char[n][m];
check = new int[n][m];
visited = new boolean[n][m];

for(int i=0; i<n; i++){
String line = br.readLine();
for(int j=0; j<m; j++){
arr[i][j] = line.charAt(j);
}
}

for(int i=0; i<n; i++){
for(int j=0; j<m; j++){
if(DFS(i, j)) answer++;
}
}
System.out.println(answer);
}

static boolean DFS(int r, int c){
if(r<0 || r>=n || c<0 || c>=m) return true; // 탈출 성공

if(visited[r][c]) return true;
if(check[r][c] == 1) return false; // 사이클

check[r][c] = 1; // 탐색 중 표시
boolean flag;
if(arr[r][c] == 'U') flag = DFS(r-1, c);
else if(arr[r][c] == 'R') flag = DFS(r, c+1);
else if(arr[r][c] == 'D') flag = DFS(r+1, c);
else flag = DFS(r, c-1);
check[r][c] = 0;

if(flag) visited[r][c] = true;
return flag;
}
}