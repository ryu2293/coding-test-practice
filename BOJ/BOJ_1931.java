import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting that) {
        if(this.end == that.end) {
            return this.start - that.start;
        }
        else {
            return this.end - that.end;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Meeting> al = new ArrayList<>();
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            al.add(new Meeting(start, end));
        }
        Collections.sort(al);

        int answer = 0;
        int time = 0;
        for(Meeting m : al) {
            if(time <= m.start) {
                time = m.end;
                answer++;
            }
        }

        System.out.println(answer);
    }
}