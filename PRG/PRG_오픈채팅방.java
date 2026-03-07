import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        ArrayList<String> answer = new ArrayList<>();

        Map<String, String> user = new HashMap<>();
        for(int i=0; i<record.length; i++) {
            String line = record[i];
            String[] split = line.split(" ");
            if(split.length == 3) {
                user.put(split[1], split[2]);
            }
        }

        for(int i=0; i<record.length; i++) {
            String line = record[i];
            String[] split = line.split(" ");

            if(split[0].equals("Change")) continue;

            String name = user.get(split[1]);
            if(split[0].equals("Enter")) {
                answer.add(name + "님이 들어왔습니다.");
            }
            else {
                answer.add(name + "님이 나갔습니다.");
            }

        }

        return answer.stream().toArray(String[]::new);
    }
}