import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> items = new HashMap<>();
        
        for(int i = 0; i < want.length; i++) {
            items.put(want[i], number[i]);
        }
        
        for(int i = 0; i < 10; i++) {
            if(items.containsKey(discount[i])) {
                items.put(discount[i], items.get(discount[i]) - 1);
            }
        }
        int answer = 0;
        boolean canbuy = true;
        for(String item : items.keySet()) {
            if(items.get(item) > 0) {
                canbuy = false;
                break;
            }
        }

        if(canbuy) {
            answer++;
        }
        
        for(int i = 10; i < discount.length; i++) {
            if(items.containsKey(discount[i])) {
                items.put(discount[i], items.get(discount[i]) - 1);
            }
            if(items.containsKey(discount[i-10])) {
                items.put(discount[i-10], items.get(discount[i-10]) + 1);
            }
            
            canbuy = true;
            for(String item : items.keySet()) {
                if(items.get(item) > 0) {
                    canbuy = false;
                    break;
                }
            }
            
            if(canbuy) {
                answer++;
            }
        }
        
        return answer;
    }
}