import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> al = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<progresses.length; i++) {
            int days = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] > 0) {
                days += 1;
            }
            q.offer(days);
        }
        
        while(!q.isEmpty()) {
            int cnt = 1;
            int day = q.poll();
            while(!q.isEmpty() && q.peek() <= day) {
                q.poll();
                cnt++;
            }
            al.add(cnt);
        }
        
        return al.stream().mapToInt(i -> i).toArray();
    }
}