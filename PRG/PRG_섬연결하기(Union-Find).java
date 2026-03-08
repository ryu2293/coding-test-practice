import java.util.*;

class Edge implements Comparable<Edge>{
    int x;
    int y;
    int cost;

    public Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge that) {
        return this.cost - that.cost;
    }
}

class Solution {
    public int find(int vex, int[] arr) {
        if(arr[vex] != vex) return arr[vex] = find(arr[vex], arr);
        else return arr[vex];
    }

    public void union(int x, int y, int[] arr) {
        int nx = find(x, arr);
        int ny = find(y, arr);
        if(nx != ny) {
            arr[nx] = ny;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<costs.length; i++) {
            pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(find(now.x, arr) == find(now.y, arr)) continue;

            union(now.x, now.y, arr);
            answer += now.cost;
        }

        return answer;
    }

}