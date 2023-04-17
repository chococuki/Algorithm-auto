import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> point = new LinkedHashMap<>();
        
        for(int i = 0; i < name.length; i++) {
            point.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        
        for(int j = 0; j < photo.length; j++) {
            for(int i = 0; i < photo[j].length; i++) {
                if(point.get(photo[j][i]) != null) answer[j] += point.get(photo[j][i]);
            }
        }
        
        return answer;
    }
}