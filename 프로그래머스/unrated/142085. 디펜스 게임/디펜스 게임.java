import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> skip = new PriorityQueue<>();
        
        int answer = 0;
        for(int i : enemy) {
            // k번까지 스킵
            if(skip.size() < k) {
                skip.add(i);
            // k번 스킵 이후
            } else {
                // 이전 스킵 보다 더 큰 적일때 스킵 위치 변경
                if(skip.peek() < i) {
                    n -= skip.poll();
                    skip.add(i);
                } else {
                    n -= i;
                }
            }
            
            if(n >= 0) {
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}