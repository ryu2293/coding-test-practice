import java.util.*;

class Edge implements Comparable<Edge>{
    int vex;
    int cost;

    public Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge that) {
        return this.cost - that.cost;
    }
}

class Solution {

    public int solution(int n, int[][] costs) {
        int answer = Integer.MAX_VALUE;

        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<costs.length; i++) {
            graph.get(costs[i][0]).add(new Edge(costs[i][1], costs[i][2]));
            graph.get(costs[i][1]).add(new Edge(costs[i][0], costs[i][2]));
        }

        answer = dij(graph, 0, n);


        return answer;
    }

    public int dij(ArrayList<ArrayList<Edge>> graph, int v, int n) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(v, 0));
        boolean[] visit = new boolean[n];
        int sum = 0;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(visit[e.vex]) continue;
            visit[e.vex] = true;
            sum += e.cost;

            for(Edge now : graph.get(e.vex)) {
                int nVex = now.vex;
                int nCost = now.cost;

                pq.offer(new Edge(nVex, nCost));
            }
        }

        System.out.println(sum);
        return sum;
    }
}