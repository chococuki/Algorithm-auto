import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        int maxNum = 0;
        for(int i : topping) {
            maxNum = Math.max(maxNum, i);
        }
        
        int[] array = new int[maxNum + 1]; // 토핑 종류, 갯수
        int countR = 0;
        for(int i : topping) {
            if(array[i] == 0) {
                countR++;
            }
            array[i]++;
        }

        
        // 자르는 위치 이동하며 토핑 갯수 비교
        boolean[] hasTopping = new boolean[maxNum + 1];
        int countL = 0;
        int answer = 0;
        for(int i : topping) {
            if(!hasTopping[i]) {
                hasTopping[i] = true;
                countL++;
            }
            if(--array[i] == 0) {
                countR--;
            }

            if(countR == countL) {
                answer++;
            } else if(countR < countL) {    // 좌측 토핑이 더 많으면 종료 
                break;
            }
        }
        
        return answer;
    }
}