import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> sub = new Stack<>();
        
        int answer = 0; // 넣은 상자 갯수
        for(int i = 0; i < order.length; i++) { // 1번 상자 부터 시작
            if(order[answer] == answer + 1) {   // 현재 상자가 order 순서일때
                answer++;
            } else {    // 아닐경우 보조벨트에 넣음
                sub.add(i+1);
            }
            while(!sub.isEmpty()) {
                if(sub.peek() == order[answer]) {
                    sub.pop();
                    answer++;
                } else {
                    break;
                }
            }
        }
        
        // 마지막으로 보조벨트 확인
        while(!sub.isEmpty()) {
            if(sub.peek() == order[answer]) {
                sub.pop();
                answer++;
            } else {
                break;
            }
        }
        
        
        return answer;
    }
}