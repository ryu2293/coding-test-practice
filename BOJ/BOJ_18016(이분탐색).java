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
        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<m; i++) {
            int target = Integer.parseInt(st.nextToken());

            int lt = lower(numbers, target);
            int rt = upper(numbers, target);
            answer.append((rt - lt) + " ");
        }

        System.out.println(answer);
    }

    public static int lower(int[] numbers, int target) {
        int lt = 0;
        int rt = numbers.length;

        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if(numbers[mid] < target) lt = mid + 1;
            else rt = mid;
        }
        return lt;
    }

    public static int upper(int[] numbers, int target) {
        int lt = 0;
        int rt = numbers.length;

        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if(numbers[mid] <= target) lt = mid + 1;
            else rt = mid;
        }
        return lt;
    }
}