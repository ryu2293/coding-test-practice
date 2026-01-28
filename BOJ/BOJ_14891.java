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
    static int[][] arr;
    static int n, l, answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            if(check(getRow(i))) answer++;
        }
        for(int i=0; i<n; i++){
            if(check(getCol(i))) answer++;
        }
        System.out.println(answer);
    }

    static int[] getRow(int r){
        int[] map = new int[n];
        for(int i=0; i<n; i++){
            map[i] = arr[r][i];
        }
        return map;
    }

    static int[] getCol(int c){
        int[] map = new int[n];
        for(int i=0; i<n; i++){
            map[i] = arr[i][c];
        }
        return map;
    }

    static boolean check(int[] map){
        boolean[] ch = new boolean[n];
        for(int i=0; i<n-1; i++){
            int diff = Math.abs(map[i] - map[i+1]);
            if(diff == 0) continue;
            if(diff > 1) return false;

            if(map[i] > map[i+1]){
                for(int j=1; j<=l; j++){
                    int pos = i+j;
                    if(pos >= n || map[i+1] != map[pos] || ch[pos]) return false;
                }
                for(int j=1; j<=l; j++){
                    int pos = i+j;
                    ch[pos] = true;
                }
            }
            else {
                for(int j=0; j<l; j++){
                    int pos = i-j;
                    if(pos < 0 || map[i] != map[pos] || ch[pos]) return false;
                }
                for(int j=0; j<l; j++){
                    int pos = i-j;
                    ch[pos] = true;
                }
            }
        }
        return true;
    }


}
