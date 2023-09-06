import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        int maxNum = 0;
        for(int i : topping) {
            maxNum = Math.max(maxNum, i);
        }
        
        int[] array = new int[maxNum + 1];
        int countLeft = 0;
        for(int i : topping) {
            if(array[i] == 0) {
                countLeft++;
            }
            array[i]++;
        }

        
        // 순서대로 이동하며 토핑 갯수 비교
        boolean[] hasTopping = new boolean[maxNum + 1];
        int countRight = 0;
        int answer = 0;
        for(int i : topping) {
            if(!hasTopping[i]) {
                hasTopping[i] = true;
                countRight++;
            }
            array[i]--;
            if(array[i] == 0) {
                countLeft--;
            }

            if(countRight == countLeft) {
                answer++;
            }
        }
        
        return answer;
    }
}