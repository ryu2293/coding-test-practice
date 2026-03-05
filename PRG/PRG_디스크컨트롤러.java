import java.util.*;

class Point implements Comparable<Point> {
    int num;
    int inTime;
    int time;

    public Point(int num, int inTime, int time) {
        this.num = num;
        this.inTime = inTime;
        this.time = time;
    }

    @Override
    public int compareTo(Point that) {
        if(this.time == that.time) {
            if(this.inTime == that.inTime) {
                return this.num - that.num;
            }
            return this.inTime - that.inTime;
        }
        return this.time - that.time;
    }
}

class Job implements Comparable<Job> {
    int num;
    int inTime;
    int time;

    public Job(int num, int inTime, int time) {
        this.num = num;
        this.inTime = inTime;
        this.time = time;
    }

    @Override
    public int compareTo(Job that) {
        return this.inTime - that.inTime;
    }
}

// 소요시간, 요청 시각, 작업 번호
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Job> q = new PriorityQueue<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0; i<jobs.length; i++) {
            q.offer(new Job(i, jobs[i][0], jobs[i][1]));
        }

        int nowTime = 0;
        int N = 0;
        while(N < jobs.length) {
            while(!q.isEmpty() && q.peek().inTime <= nowTime) {
                Job job = q.poll();
                pq.offer(new Point(job.num, job.inTime, job.time));
            }

            if(!pq.isEmpty()) {
                Point job = pq.poll();
                nowTime += job.time;
                answer += (nowTime - job.inTime);
                N++;
            }
            else {
                nowTime++;
            }
        }

        return answer / N;
    }
}