import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] open = new boolean[cards.length];
        
        List<Integer> group = new ArrayList<>();
        
        int cnt = 0;
        for(int i = 0; i < cards.length; i++) {
            if(!open[i]) {
                int now = i;
                while(!open[now]) {
                    open[now] = true;
                    cnt++;
                    now = cards[now] - 1;
                }
                group.add(cnt);
                cnt = 0;
            }
        }
        
        Collections.sort(group, Collections.reverseOrder());
        
        int answer = 0;
        if(group.size() >= 2) {
            answer = group.get(0) * group.get(1);
        }
        
        return answer;
    }
}