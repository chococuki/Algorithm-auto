import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        String last = words[0];
        String now = words[1];
        
        Set<String> used = new HashSet<>();
        used.add(last);
        if(last.charAt(last.length() - 1) != now.charAt(0) || used.contains(now)) {
            answer[0] = 2;
            answer[1] = 1;
            return answer;
        } else {
            used.add(now);
        }
        
        
        for(int i = 2; i < words.length; i++) {
            last = now;
            now = words[i];
            
            if(used.contains(now)) {
                answer[0] = 1 + i % n;
                answer[1] = 1 + i / n;
                return answer;
            } else {
                used.add(now);
            }
            
            if(last.charAt(last.length() - 1) != now.charAt(0)) {
                answer[0] = 1 + i % n;
                answer[1] = 1 + i / n;
                return answer;
            }
        }

        return answer;
    }
}