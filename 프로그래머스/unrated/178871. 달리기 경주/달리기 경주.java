import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = players;
        Map<String, Integer> ranking = new LinkedHashMap<>();
        
        for(int i = 0; i < players.length; i++) {
            ranking.put(players[i], i);
        }
        
        for(String call : callings) {
            int index = ranking.get(call);
            
            String tmp = answer[index-1];
            answer[index-1] = answer[index];
            answer[index] = tmp;
            
            ranking.put(call, ranking.get(call) - 1);
            ranking.put(tmp, ranking.get(tmp) + 1);
        }
        
        
        return answer;
    }
}