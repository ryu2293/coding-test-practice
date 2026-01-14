import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> parking = new HashMap<>();
        Map<String, Integer> status = new TreeMap<>();
        ArrayList<Integer> al = new ArrayList<>();

        StringTokenizer st;
        for (String s : records) {
            st = new StringTokenizer(s);
            String time = st.nextToken();
            String num = st.nextToken();
            String type = st.nextToken();

            st = new StringTokenizer(time, ":");
            int min = Integer.parseInt(st.nextToken()) * 60
                    + Integer.parseInt(st.nextToken());

            if (type.equals("IN")) {
                parking.put(num, min);
            } else {
                int inTime = parking.remove(num);
                int parkingTime = min - inTime;
                status.put(num, status.getOrDefault(num, 0) + parkingTime);

            }

        }

        Set<String> keySet = parking.keySet();
        for (String key : keySet) {
            int parkingTime = 23 * 60 + 59 - parking.get(key);
            status.put(key, status.getOrDefault(key, 0) + parkingTime);
        }

        for (Map.Entry<String, Integer> entry : status.entrySet()) {
            int value = entry.getValue();
            int fee = 0;

            if (value <= fees[0]) {
                fee = fees[1];
            } else {
                int exceededTime = value - fees[0];

                int plusTime = exceededTime % fees[2] > 0 ? 1 : 0;
                fee = fees[1] + (exceededTime / fees[2] + plusTime) * fees[3];
            }

            al.add(fee);
        }


        int[] answer = al.stream().mapToInt(i -> i).toArray();


        return answer;
    }
}