import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Point {
    int height;
    int order;

    public Point(int height, int order) {
        this.height = height;
        this.order = order;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Point> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                stack.push(new Point(height, i));
                answer.append("0 ");
            } else {
                while (!stack.isEmpty() && stack.peek().height < height) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    answer.append("0 ");
                    stack.push(new Point(height, i));
                } else {
                    answer.append(stack.peek().order + " ");
                    stack.push(new Point(height, i));
                }
            }

        }
        System.out.println(answer);
    }
}