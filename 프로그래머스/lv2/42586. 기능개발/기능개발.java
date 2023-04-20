import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        int index = 0;
        int day = 0;
        int count = 0;
        while(index < progresses.length) {
            if((progresses[index] + speeds[index] * day) >= 100) {
                count++;
                index++;
            } else {
                if(count != 0) result.add(count);
                count = 0;
                day++;
            }
        }
        result.add(count);
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}