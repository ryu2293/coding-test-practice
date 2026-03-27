import java.util.*;

class Solution {
    long answer;
    ArrayList<Long> numbers;
    ArrayList<Character> op;
    public long solution(String expression) {
        answer = 0;

        numbers = new ArrayList<>();
        op = new ArrayList<>();

        String number = "";
        for(char c : expression.toCharArray()) {
            if(c == '*' || c == '+' || c == '-') {
                op.add(c);
                numbers.add(Long.parseLong(number));
                number = "";
            }
            else {
                number += c;
            }
        }
        numbers.add(Long.parseLong(number));

        ArrayList<Character> oper = new ArrayList<>();
        for(char c : op) {
            if(oper.contains(c)) continue;
            oper.add(c);
        }
        ArrayList<Character> priority = new ArrayList<Character>();
        boolean[] visit = new boolean[oper.size()];

        dfs(0, visit, oper, priority);

        return answer;
    }

    public void dfs(int idx, boolean[] visit, ArrayList<Character> oper, ArrayList<Character> priority) {
        if(idx == oper.size()) {
            calculate(priority);
        }
        else {
            for(int i=0; i<oper.size(); i++) {
                if(visit[i]) continue;
                visit[i] = true;
                priority.add(oper.get(i));
                dfs(idx + 1, visit, oper, priority);
                priority.remove(priority.size() - 1);
                visit[i] = false;
            }
        }
    }

    public void calculate(ArrayList<Character> priority) {
        ArrayList<Long> num = new ArrayList<>(numbers);
        ArrayList<Character> o = new ArrayList<>(op);

        for(char c : priority) {
            for(int i=0; i<o.size(); i++) {
                if(o.get(i) == c) {
                    long result = sum(num.get(i), num.get(i+1), c);
                    num.set(i, result);
                    num.remove(i+1);
                    o.remove(i);
                    i--;
                }
            }
        }

        answer = Math.max(answer, Math.abs(num.get(0)));
    }

    public long sum(long x, long y, char op) {
        if(op == '+') return x+y;
        else if (op == '-') return x-y;
        else return x*y;
    }
}