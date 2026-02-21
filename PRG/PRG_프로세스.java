import java.util.*;

class Point {
    int pri;
    int loc;

    public Point(int pri, int loc) {
        this.pri = pri;
        this.loc = loc;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Point> q = new LinkedList<>();

        for(int i=0; i<priorities.length; i++) {
            q.offer(new Point(priorities[i], i));
            pq.offer(priorities[i]);
        }

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.pri != pq.peek()) {
                q.offer(now);
            }
            else {
                if(now.loc == location) {
                    return answer;
                }
                pq.poll();
                answer++;
            }
        }

        return answer;
    }
}