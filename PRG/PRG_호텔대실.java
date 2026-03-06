import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        int[][] times = new int[book_time.length][2];
        for(int i=0; i<book_time.length; i++) {
            int start = change(book_time[i][0]);
            int end = change(book_time[i][1])+10;
            times[i][0] = start;
            times[i][1] = end;
        }

        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<times.length; i++) {
            int start = times[i][0];
            int end = times[i][1];

            if(pq.isEmpty()) {
                pq.offer(end);
                continue;
            }

            if(pq.peek() <= start) {
                pq.poll();
            }
            pq.offer(end);
        }

        return pq.size();
    }

    public int change(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}