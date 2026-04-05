import java.util.*;
/*
    Map으로 장르별로 재생 횟수를 기록.
    Map 크기만큼 반복, 장르를 탐색하여 가장 많이 재생된 장르를 우선순위큐에 넣음
    장르내에서 많이 재생된 노래 -> 고유번호가 낮은 노래 순서로 정렬.
*/

class Point implements Comparable<Point>{
    int num;
    String genre;
    int play;

    public Point(int num, String genre, int play) {
        this.num = num;
        this.genre = genre;
        this.play = play;
    }

    @Override
    public int compareTo(Point that) {
        if(this.play == that.play) {
            return this.num - that.num;
        } else {
            return that.play - this.play;
        }
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = genres.length;
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<n; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());


        for(Map.Entry<String, Integer> entry : list) {
            String genre = entry.getKey();
            PriorityQueue<Point> pq = new PriorityQueue<>();

            for(int i=0; i<n; i++) {
                if(genre.equals(genres[i])) {
                    pq.offer(new Point(i, genres[i], plays[i]));
                }
            }

            int cnt = 0;
            while(!pq.isEmpty()) {
                if(cnt == 2) break;
                Point p = pq.poll();
                answer.add(p.num);
                cnt++;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}