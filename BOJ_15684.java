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
    static int n, m, h, answer = Integer.MAX_VALUE;
    static int[][] arr;
    static ArrayList<Point> al = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new int[h+1][n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1;
        }

        for(int i=1; i<=h; i++){
            for(int j=1; j<n; j++){
                if(arr[i][j] == 1) continue;
                al.add(new Point(i, j));
            }
        }

        // 3이 넘어가면 더 이상 확인할 필요 X.
        for(int i=0; i<=3; i++){
            comb(0, i, 0);
        }

        System.out.println((answer==Integer.MAX_VALUE)?-1:answer);

    }

    // 가로선 추가
    static void comb(int L, int cnt, int x){
        if(L == cnt){
            if(simulation()){
                answer = Math.min(answer, cnt);
            }
        }
        else{
            for(int i=x; i<al.size(); i++){
                Point now = al.get(i);
                if(canPos(now.x, now.y)){
                    arr[now.x][now.y] = 1;
                    comb(L+1, cnt, i+1);
                    arr[now.x][now.y] = 0;
                }
            }
        }
    }

    // 세로선의 결과가 변하지 않는 지 시뮬레이션
    static boolean simulation(){
        for(int col=1; col<=n; col++){
            int pos = col;
            for(int row=1; row<=h; row++){
                if(pos<n && arr[row][pos] == 1){
                    pos = pos+1;
                }
                else if(pos>1 && arr[row][pos-1] == 1){
                    pos = pos-1;
                }
            }
            if(col != pos) return false;
        }
        return true;
    }

    // 사다리를 놓을 수 있는 위치인지 확인
    static boolean canPos(int x, int y){
        if(y<1 || y>=n) return false;
        if(arr[x][y] == 1) return false;
        if(y+1 < n && arr[x][y+1]==1) return false;
        if(y-1 >= 1 && arr[x][y-1]==1) return false;
        return true;
    }
}