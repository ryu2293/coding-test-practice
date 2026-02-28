import java.util.*;
import java.util.regex.*;

class Solution {
    public String solution(String new_id) {
        String s = new_id.toLowerCase();
        s = s.replaceAll("[^a-z0-9-_.]", "");
        System.out.println(s);
        s = s.replaceAll("\\.{2,}", ".");
        System.out.println(s);
        s = s.replaceAll("^\\.|\\.$", "");
        System.out.println(s);
        if(s.isEmpty()) s = "a";
        if(s.length() >= 16) {
            s = s.substring(0, 15);
            s = s.replaceAll("\\.$", "");
            System.out.println(s);
        }
        if(s.length() <= 2) {
            char last = s.charAt(s.length() - 1);
            while(s.length() < 3) {
                s += last;
            }
        }
        System.out.println(s);

        return s;
    }
}