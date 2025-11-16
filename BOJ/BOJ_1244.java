import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int gender;
    int pos;

    public Point(int gender, int pos) {
        this.gender = gender;
        this.pos = pos;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int students = Integer.parseInt(st.nextToken());

        Point[] student = new Point[students];
        for (int i = 0; i < students; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            student[i] = new Point(gender, pos);
        }

        for (Point p : student) {
            if (p.gender == 1) {
                for (int i = p.pos; i <= N; i = i + p.pos) {
                    toggle(i, arr);
                }
            } else {
                toggle(p.pos, arr);
                int lPos = p.pos;
                int rPos = p.pos;
                while (true) {
                    lPos--;
                    rPos++;
                    if (lPos < 1 || rPos > N) {
                        break;
                    }

                    if (arr[lPos] != arr[rPos]) {
                        break;
                    }

                    toggle(lPos, arr);
                    toggle(rPos, arr);
                }
            }

        }
        for (int i = 1; i <= N; i++) {
            System.out.print(arr[i] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

    static void toggle(int pos, int[] arr){
        if(arr[pos] == 1) {
            arr[pos] = 0;
        }
        else{
            arr[pos] = 1;
        }
    }
}
