import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

class Point implements Comparable<Point>{
    int num;
    int cnt;

    public Point(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Point o){
        if(this.cnt == o.cnt) return this.num - o.num;
        else return this.cnt - o.cnt;
    }
}

public class Main{
    static int r, c, k;
    static int xLen=3, yLen=3;
    static int[][] arr = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());

    }

    static int solution(){
        for(int t=0; t<=100; t++){
            if(arr[r][c] == k) return t;
            size();
        }
        return -1;
    }

    static void size(){
        int xl = xLen; int yl = yLen;
        if(xLen >= yLen) R(yl);
        else C(xl);
    }

    static void R(int yl){
        for(int i=1; i<=xLen; i++){
            Map<Integer, Integer> map = new HashMap<>();
            List<Point> li = new ArrayList<>();
            for(int j=1; j<=yl; j++){
                int key = arr[i][j];
                if(key == 0) continue;
                map.put(key, map.getOrDefault(key, 0)+1);
            }

            for(Map.Entry<Integer, Integer> e : map.entrySet()){
                li.add( new Point(e.getKey(), e.getValue()));
            }
            Collections.sort(li);

            int col = 1;
            for(Point p : li){
                arr[i][col++] = p.num;
                arr[i][col++] = p.cnt;
            }
            yLen = Math.max(yLen, col);

            while (col <= 100){
                arr[i][col++] = 0;
            }
        }

    }

    static void C(int xl){
        for(int i=1; i<=yLen; i++){
            Map<Integer, Integer> map = new HashMap<>();
            List<Point> li = new ArrayList<>();
            for(int j=1; j<=xl; j++){
                int key = arr[j][i];
                if(key == 0) continue;
                map.put(key, map.getOrDefault(key, 0)+1);
            }

            for(Map.Entry<Integer, Integer> e : map.entrySet()){
                li.add( new Point(e.getKey(), e.getValue()));
            }
            Collections.sort(li);

            int row = 1;
            for(Point p : li){
                arr[row++][i] = p.num;
                arr[row++][i] = p.cnt;
            }
            xLen = Math.max(xLen, row);

            while (row <= 100){
                arr[row++][i] = 0;
            }
        }
    }
}