import java.util.*;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();

        for (String line : info) {
            String[] token = line.split(" ");
            boolean[] visit = new boolean[4];
            for (int i = 1; i <= 4; i++) {
                dfs(0, 0, i, token, visit);
            }
            // 원본 key 저장
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) sb.append(token[i].trim() + " ");
            String key = sb.toString().trim();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(Integer.parseInt(token[4]));
        }

        // 모든 리스트 정렬
        for (List<Integer> list : map.values()) Collections.sort(list);

        for (String line : query) {
            String[] token = line.split("and");
            String[] tok = token[3].trim().split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) sb.append(token[i].trim() + " ");
            sb.append(tok[0].trim() + " ");
            String key = sb.toString().trim();

            int score = Integer.parseInt(tok[1]);
            List<Integer> userScores = map.get(key);
            if (userScores == null) { answer.add(0); continue; }

            // 이분탐색으로 score 이상인 개수 찾기
            int lt = 0, rt = userScores.size();
            while (lt < rt) {
                int mid = (lt + rt) / 2;
                if (userScores.get(mid) < score) lt = mid + 1;
                else rt = mid;
            }
            answer.add(userScores.size() - lt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public void dfs(int start, int n, int r, String[] token, boolean[] visit) {
        if (n == r) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (visit[i]) sb.append("- ");
                else sb.append(token[i].trim() + " ");
            }
            String key = sb.toString().trim();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(Integer.parseInt(token[4]));
        } else {
            for (int i = start; i < 4; i++) {
                if (visit[i]) continue;
                visit[i] = true;
                dfs(i + 1, n + 1, r, token, visit);
                visit[i] = false;
            }
        }
    }
}