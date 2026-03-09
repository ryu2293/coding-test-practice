import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            pq.offer(num);
        }

        while (!pq.isEmpty()) {
            int[] arr = new int[3];
            for(int i=0; i<3; i++) {
                if(!pq.isEmpty()) {
                    arr[i] = pq.poll();
                }
            }

            if(arr[0] >= arr[1] + arr[2]) {
                if(pq.isEmpty()) {
                    System.out.println(-1);
                    break;
                }
                arr[0] = pq.poll();

                for(int x : arr) {
                    pq.offer(x);
                }
            }
            else {
                System.out.println(arr[0] + arr[1] + arr[2]);
                break;
            }
        }


    }
}