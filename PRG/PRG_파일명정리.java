import java.util.*;
import java.util.regex.*;

class Point implements Comparable<Point> {
    int order;
    String head;
    int number;
    String st;

    public Point(int order, String head, int number, String st) {
        this.order = order;
        this.head = head;
        this.number = number;
        this.st = st;
    }

    @Override
    public int compareTo(Point that) {
        if(this.head.equals(that.head)) {
            if(this.number == that.number) {
                return this.order - that.order;
            }
            else {
                return this.number - that.number;
            }
        }
        else {
            return this.head.compareTo(that.head);
        }
    }
}

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        Pattern p = Pattern.compile("^([a-zA-Z\\s.-]+)([0-9]{1,5})(.*)$");
        List<Point> list = new ArrayList<>();

        for(int i=0; i < files.length; i++) {
            String s = files[i];
            Matcher m = p.matcher(s);
            if(m.find()) {
                String head = m.group(1);
                String number = m.group(2);
                String tail = m.group(3);

                String upper = head.toUpperCase();
                int num = Integer.parseInt(number);
                list.add(new Point(i, upper, num, s));

                System.out.println(i + " " + upper + " " + num + " " + s);
            }

        }

        Collections.sort(list);
        answer = list.stream()
                .map(o -> o.st)
                .toArray(String[]::new);

        return answer;
    }
}