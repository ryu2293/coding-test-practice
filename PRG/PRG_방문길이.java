import java.util.*;

/*
    -5 ~ +5 좌표.
    명령마다 1칸씩 이동.
    처음 걸어본 길의 길이 구하기.
*/

class Solution {

    public int solution(String dirs) {
        int answer = 0;

        answer = bfs(dirs);

        return answer;
    }

    public int bfs(String dirs) {
        int x = 5;
        int y = 5;
        int dist = 0;

        boolean[][][] visit = new boolean[11][11][4];
        for(char c : dirs.toCharArray()) {
            if(c == 'U') {
                if(y <= 0) continue;
                y--;

                if(visit[x][y][0]) continue;
                dist++;
                visit[x][y][0] = true;
                visit[x][y+1][2] = true;
            } else if (c == 'D') {
                if(y >= 10) continue;
                y++;

                if(visit[x][y][2]) continue;
                dist++;
                visit[x][y][2] = true;
                visit[x][y-1][0] = true ;
            } else if (c == 'R') {
                if(x >= 10) continue;
                x++;

                if(visit[x][y][1]) continue;
                dist++;
                visit[x][y][1] = true;
                visit[x-1][y][3] = true;
            } else {
                if(x <= 0) continue;
                x--;

                if(visit[x][y][3]) continue;
                dist++;
                visit[x][y][3] = true;
                visit[x+1][y][1] = true;
            }
        }

        return dist;
    }
}