import java.util.*;

class Node {
    int ver, dist;
    public Node(int ver, int dist) {
        this.ver = ver;
        this.dist = dist;
    }
}

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return bfs(n, graph);
    }

    public int bfs(int n, ArrayList<ArrayList<Integer>> graph) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0));

        boolean[] visit = new boolean[n+1];
        visit[1] = true;
        int maxDist = 0, cnt = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int next : graph.get(node.ver)) {
                if (visit[next]) continue;
                visit[next] = true;
                int dist = node.dist + 1;
                q.offer(new Node(next, dist));

                if (maxDist < dist) {
                    maxDist = dist;
                    cnt = 0;
                }
                if (maxDist == dist) cnt++;
            }
        }
        return cnt;
    }
}