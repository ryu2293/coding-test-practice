import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[4][8];
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0; j<8; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int di = Integer.parseInt(st.nextToken());

            int[] dir = new int[4];
            direct(num-1, di, dir);
            rotate(dir);
        }

        int answer = 0;
        for(int i=0; i<4; i++) {
            if(arr[i][0] == 1) {
                answer += (1 << i);
            }
        }
        System.out.println(answer);
    }

    static void rotate(int[] dir) {
        for(int i=0; i<4; i++) {
            if(dir[i] == 1) {
                int temp = arr[i][7];
                for(int j=7; j>0; j--) {
                    arr[i][j] = arr[i][j-1];
                }
                arr[i][0] = temp;
            }
            else if(dir[i] == -1) {
                int temp = arr[i][0];
                for(int j=0; j<7; j++) {
                    arr[i][j] = arr[i][j+1];
                }
                arr[i][7] = temp;
            }
        }
    }

    static void direct(int num, int di, int[] dir) {
        dir[num] = di;

        for(int i = num-1; i >= 0; i--) {
            if(arr[i+1][6] != arr[i][2]) {
                dir[i] = dir[i+1] * -1;
            }
            else {
                break;
            }
        }

        for(int i = num+1; i < 4; i++) {
            if(arr[i-1][2] != arr[i][6]) {
                dir[i] = dir[i-1] * -1;
            }
            else {
                break;
            }
        }
    }
}