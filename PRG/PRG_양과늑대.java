import java.util.*;

class State {
    int vex;
    int sheep;
    int wolf;
    List<Integer> canVisit;

    public State(int vex, int sheep, int wolf, List<Integer> canVisit) {
        this.vex = vex;
        this.sheep = sheep;
        this.wolf = wolf;
        this.canVisit = canVisit;
    }
}
// 그래프로 vex를 연결한다. 양방향으로
// bfs로 루트부터 탐색한다.
// 다시 돌아가는 경우도 존재하기 때문에 방문 처리는 하지 않는다.
class Solution {
    ArrayList<ArrayList<Integer>> graph;
    int n;
    int answer;

    public int solution(int[] info, int[][] edges) {
        answer = 0;
        n = info.length;

        graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] x : edges) {
            graph.get(x[0]).add(x[1]);
        }

        bfs(info);

        return answer;
    }

    public void bfs(int[] info) {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(0, 1, 0, graph.get(0)));

        while(!q.isEmpty()) {
            State s = q.poll();

            answer = Math.max(answer, s.sheep);

            for(int next : s.canVisit) {
                List<Integer> newCanVisit = new ArrayList<>(s.canVisit);
                newCanVisit.remove(Integer.valueOf(next));
                newCanVisit.addAll(graph.get(next));

                if(info[next] == 0) {
                    q.offer(new State(next, s.sheep + 1, s.wolf, newCanVisit));
                } else {
                    if(s.sheep <= s.wolf + 1) continue;
                    q.offer(new State(next, s.sheep, s.wolf + 1, newCanVisit));
                }
            }
        }

    }
}