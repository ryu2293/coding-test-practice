import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char[][] nor = new char[n][n];
        char[][] rgb = new char[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0; j<n; j++) {
                char color = line.charAt(j);
                nor[i][j] = color;
                rgb[i][j] = color == 'G' ? 'R' : color;
            }
        }

        int norCnt = countArea(n, nor);
        int rgbCnt = countArea(n, rgb);

        System.out.print(norCnt + " " + rgbCnt);
    }

    static int countArea(int n, char[][] map) {
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] != 'c') {
                    dfs(i, j, n, map[i][j], map);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void dfs(int x, int y, int n, char color, char[][] nor) {
        if(nor[x][y] != color) return;
        else {
            nor[x][y] = 'c';
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                dfs(nx, ny, n, color, nor);
            }
        }
    }
}