import java.util.*;

class Solution {
    public String solution(String s) {
        
        StringBuilder answer = new StringBuilder();
        boolean isFirst = true;
        int size = s.length();
        for(int i = 0; i < size; i++) {
            String next = Character.toString(s.charAt(i));
            
            if(next.equals(" ")) {
                answer.append(next);
                isFirst = true;
            } else if(isFirst) {
                answer.append(next.toUpperCase());
                isFirst = false;
            } else {
                answer.append(next.toLowerCase());
            }
        }
        
        return answer.toString();
    }
}