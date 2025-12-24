class Solution {
    static int answer;

    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, 0, 0);
        return answer;
    }

    static void dfs(int[] numbers, int target, int pos, int sum) {
        if (pos == numbers.length) {
            if (sum == target) answer++;
            return;
        }

        dfs(numbers, target, pos + 1, sum + numbers[pos]);
        dfs(numbers, target, pos + 1, sum - numbers[pos]);
    }
}
