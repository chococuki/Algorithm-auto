import java.util.*;

class Solution {
    public String solution(String s) {
        
        StringBuilder answer = new StringBuilder();
        boolean isFirst = true;
        
        for(char c : s.toCharArray()) { // 한 글자씩 확인
            if(c == ' ') {
                answer.append(c);
                isFirst = true;
            } else if(isFirst) {    // 첫 글자면 대문자
                answer.append(Character.toUpperCase(c));
                isFirst = false;
            } else {                // 나머지 소문자
                answer.append(Character.toLowerCase(c));
            }
        }
        
        return answer.toString();
    }
}