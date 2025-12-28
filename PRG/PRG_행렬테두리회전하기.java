import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int size = queries.length;
        int[] answer = new int[size];
        int[][] arr = new int[rows+1][columns+1];

        int value = 1;
        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=columns; j++) {
                arr[i][j] = value++;
            }
        }

        int idx = 0;
        for(int[] quer : queries) {
            int x1 = quer[0];
            int y1 = quer[1];
            int x2 = quer[2];
            int y2 = quer[3];
            
            int min = Integer.MAX_VALUE;
            
            int temp = arr[x1][y1];
            min = Math.min(min, temp);
            for(int i = x1; i < x2; i++) {
                arr[i][y1] = arr[i+1][y1];
                min = Math.min(min, arr[i+1][y1]);
            }
            
            for(int i=y1; i<y2; i++) {
                arr[x2][i] = arr[x2][i+1];
                min = Math.min(min, arr[x2][i+1]);
            }
            
            for(int i=x2; i>x1; i--) {
                arr[i][y2] = arr[i-1][y2];
                min = Math.min(min, arr[i-1][y2]);
            }
            
            for(int i=y2; i>y1; i--) {
                arr[x1][i] = arr[x1][i-1];
                min = Math.min(min, arr[x1][i-1]);
            }
            
            arr[x1][y1+1] = temp;
            
            answer[idx] = min;
            idx++;
        }

        return answer;
        
    }
}