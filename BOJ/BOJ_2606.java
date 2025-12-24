import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int answer = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n+1];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited[1] = true;
        dfs(1);
        System.out.println(answer);
    }

    static void dfs(int idx) {
        for(int x : graph.get(idx)) {
            if(!visited[x]) {
                visited[x] = true;
                answer++;
                dfs(x);
            }
        }
    }



}