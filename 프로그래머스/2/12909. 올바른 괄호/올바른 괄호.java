import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        char[] ch = s.toCharArray();
        
        int count = 0;
        for(char c : ch) {
            if(c == '(') {
                count++;
            } else {
                if(count == 0) {
                    answer = false;
                    break;
                } else {
                    count--;
                }
            }
        }
        
        if(count != 0) {
            answer = false;
        }

        return answer;
    }
}