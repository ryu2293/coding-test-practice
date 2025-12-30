import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1, end = 1;
        int sum = 0;
        int answer = 0;
        while (true) {
            if (sum >= m){
                sum -= arr[start++];
            }
            else if(end > n) {
                break;
            }
            else {
                sum += arr[end++];
            }

            if(sum ==m) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}