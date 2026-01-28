import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        boolean[] visit = new boolean[n+1];

        dfs(n, m, 0, arr, visit);
        System.out.println(sb.toString());
    }

    static void dfs(int n, int m, int depth, int[] arr, boolean[] visit) {
        if(depth == m) {
            for(int num : arr) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(!visit[i]) {
                arr[depth] = i;
                visit[i] = true;
                dfs(n, m, depth + 1, arr, visit);
                visit[i] = false;
            }
        }
    }
}