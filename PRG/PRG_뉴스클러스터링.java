import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String str1, String str2) {

        String upStr1 = str1.toUpperCase();
        String upStr2 = str2.toUpperCase();

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for(int i=0; i < upStr1.length() - 1; i++) {
            String sub = upStr1.substring(i, i+2);
            if(Pattern.matches("^[A-Z]*$", sub)) {
                map1.put(sub, map1.getOrDefault(sub, 0) + 1);
            }
        }

        for(int i=0; i < upStr2.length() - 1; i++) {
            String sub = upStr2.substring(i, i+2);
            if(Pattern.matches("^[A-Z]*$", sub)) {
                map2.put(sub, map2.getOrDefault(sub, 0) + 1);
            }
        }

        if(map1.isEmpty() && map2.isEmpty()) return 65536;

        double intersect = 0;
        double union = 0;

        for(String key : map1.keySet()){
            if(map2.containsKey(key)) {
                int size1 = map1.get(key);
                int size2 = map2.get(key);
                intersect = intersect + Math.min(size1, size2);
            }
        }

        for(String key : map1.keySet()){
            if(map2.containsKey(key)) {
                int size1 = map1.get(key);
                int size2 = map2.get(key);
                union = union + Math.max(size1, size2);
            }
            else {
                union += map1.get(key);
            }
        }

        for(String key : map2.keySet()) {
            if(!map1.containsKey(key)) {
                union += map2.get(key);
            }
        }

        return (int)(intersect / union * 65536);
    }
}