import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        // map에 토핑종류, 갯수 저장
        Map<Integer, Integer> mapA = new LinkedHashMap<>();
        Map<Integer, Integer> mapB = new LinkedHashMap<>();
        
        // 한쪽에 모든 토핑
        for(int i : topping) {
            mapA.put(i, mapA.getOrDefault(i, 0) + 1);
        }
        
        // 순서대로 이동하며 토핑 갯수 비교
        int answer = 0;
        for(int i : topping) {
            mapB.put(i, mapA.getOrDefault(i, 0) + 1);
            
            if(mapA.get(i) == 1) {
                mapA.remove(i);
            } else {
                mapA.put(i, mapA.get(i) - 1);
            }
            
            if(mapA.size() == mapB.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}