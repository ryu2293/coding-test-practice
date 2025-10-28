import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = Integer.MIN_VALUE;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            if(stack.isEmpty()){
                stack.push(i);
            }
            else{
                if(arr[stack.peek()] > arr[i]){
                    while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                        int height = arr[stack.pop()];
                        int width = 0;
                        if(stack.isEmpty()){
                            width = i;
                        }
                        else{
                            width = i - stack.peek() - 1;
                        }
                        int size = height * width;
                        answer = Math.max(answer, size);
                    }
                    stack.push(i);
                }
                else{
                    stack.push(i);
                }
            }
        }
        while (!stack.isEmpty()){
            int idx = stack.pop();
            int width = n;
            if(!stack.isEmpty()){
                width = n - stack.peek() - 1;
            }

            int height = arr[idx];
            int size = width * height;
            answer = Math.max(answer, size);
        }
        System.out.println(answer);
    }
}