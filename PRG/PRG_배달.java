import java.util.*;

class Edge implements Comparable<Edge> {
    int vex;
    int val;

    public Edge(int vex, int val) {
        this.vex = vex;
        this.val = val;
    }

    @Override
    public int compareTo(Edge that) {
        return this.val - that.val;
    }

}

class Solution {
    ArrayList<ArrayList<Edge>> graph;
    int[] dis;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        graph = new ArrayList<>();
        dis = new int[N+1];

        Arrays.fill(dis, Integer.MAX_VALUE);

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        dijkstra();

        for(int i=1; i<=N; i++) {
            if(dis[i] <= K) answer++;
        }

        return answer;
    }

    void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        dis[1] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int vex = now.vex;
            int val = now.val;
            if(dis[vex] < val) continue;

            for(Edge next : graph.get(vex)) {
                if(dis[next.vex] > val + next.val) {
                    dis[next.vex] = val + next.val;
                    pq.offer(new Edge(next.vex, val + next.val));
                }
            }
        }


    }
}