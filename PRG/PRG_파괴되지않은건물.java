import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int row = board.length;
        int col = board[0].length;
        int[][] diff = new int[row+1][col+1];

        for(int i=0; i<skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int r2 = skill[i][3];
            int c1 = skill[i][2];
            int c2 = skill[i][4];
            int degree = skill[i][5];

            if(type == 1) {
                diff[r1][c1] -= degree;
                diff[r1][c2+1] += degree;
                diff[r2+1][c1] += degree;
                diff[r2+1][c2+1] -= degree;
            }
            else {
                diff[r1][c1] += degree;
                diff[r1][c2+1] -= degree;
                diff[r2+1][c1] -= degree;
                diff[r2+1][c2+1] += degree;
            }
        }

        for(int i=0; i<=row; i++) {
            for(int j=1; j<=col; j++) {
                diff[i][j] += diff[i][j-1];
            }
        }

        for(int j=0; j<=col; j++) {
            for(int i=1; i<=row; i++) {
                diff[i][j] += diff[i-1][j];
            }
        }

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                board[i][j] += diff[i][j];
                if(board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}