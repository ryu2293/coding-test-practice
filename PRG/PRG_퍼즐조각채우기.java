import java.util.*;

// 게임보드에서 0인 곳을 찾아서 0,0 위치에 맞춰 정사각형 2차원 배열을 만든다.
// 테이블에서 1인 곳을 찾아 0,0 위치에 맞춰 정사각형 2차원 배열을 만든다.
// 퍼즐을 돌려가며 끼워 넣는다.

class Solution {
    int n;
    int[] dx = {0, -1, 0, 1};
    int[] dy = {-1, 0, 1, 0};
    boolean[][] visit;

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        n = game_board.length;

        visit = new boolean[n][n];
        List<List<int[]>> boardGroup = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(game_board[i][j] == 0 && !visit[i][j]) {
                    List<int[]> board = new ArrayList<>();
                    bfs(i, j, 0, game_board, board);
                    pos(board);
                    boardGroup.add(board);
                }
            }
        }

        visit = new boolean[n][n];
        List<List<int[]>> puzzleGroup = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(table[i][j] == 1 && !visit[i][j]) {
                    List<int[]> puzzle = new ArrayList<>();
                    bfs(i, j, 1, table, puzzle);
                    pos(puzzle);
                    puzzleGroup.add(puzzle);
                }
            }
        }

        boolean[] used = new boolean[puzzleGroup.size()];
        for(List<int[]> g1 : boardGroup) {
            boolean flag = false;
            for(int i=0; i<puzzleGroup.size(); i++) {
                if(used[i]) continue;

                List<int[]> g2 = puzzleGroup.get(i);
                for(int j=0; j<4; j++) {
                    if(check(g1, g2)) {
                        used[i] = true;
                        answer += g1.size();
                        flag = true;
                        break;
                    }

                    rotate(g2);
                }
                if(flag) break;
            }
        }


        return answer;
    }

    public boolean check(List<int[]> g1, List<int[]> g2) {
        if(g1.size() != g2.size()) return false;

        g1.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        g2.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for(int i=0; i<g1.size(); i++) {
            int[] x = g1.get(i);
            int[] y = g2.get(i);

            if(x[0] != y[0] || x[1] != y[1]) return false;
        }

        return true;
    }

    public void rotate(List<int[]> list) {
        for(int[] arr : list) {
            int temp = arr[0];
            arr[0] = arr[1];
            arr[1] = -temp;
        }

        pos(list);
    }

    public void pos(List<int[]> list) {
        int minX = list.stream().mapToInt(a -> a[0]).min().getAsInt();
        int minY = list.stream().mapToInt(a -> a[1]).min().getAsInt();

        for(int[] arr : list) {
            arr[0] -= minX;
            arr[1] -= minY;
        }
    }

    public void bfs(int x, int y, int val, int[][] board, List<int[]> list) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            list.add(new int[]{now[0], now[1]});

            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visit[nx][ny] == true) continue;
                if(board[nx][ny] != val) continue;

                visit[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}