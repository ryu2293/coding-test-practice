import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String line = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek() < line.charAt(i) && k>0) {
                    stack.pop();
                    k--;
                }
                stack.push(line.charAt(i));
            } else {
                stack.push(line.charAt(i));
            }
        }

        while (k>0){
            stack.pop();
            k--;
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        String answer = sb.reverse().toString();
        System.out.println(answer);
    }
}