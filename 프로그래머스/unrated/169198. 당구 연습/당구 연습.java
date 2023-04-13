import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < balls.length; i++) {
            int ballx = balls[i][0];
            int bally = balls[i][1];
            
            int tmpResult = m*m + n*n;
            
            // 상 대칭
            int res1 = (int)Math.pow((startX-(ballx)), 2) + (int)Math.pow((startY-(2*n-bally)), 2);
            // 하 대칭
            int res2 = (int)Math.pow((startX-(ballx)), 2) + (int)Math.pow((startY-(-bally)), 2);
            // 좌 대칭
            int res3 = (int)Math.pow((startX-(-ballx)), 2) + (int)Math.pow((startY-(bally)), 2);
            // 우 대칭
            int res4 = (int)Math.pow((startX-(2*m-ballx)), 2) + (int)Math.pow((startY-(bally)), 2);
            
            if(startX == ballx) {
                if(startY < bally) {
                    res1 = tmpResult;
                } else {
                    res2 = tmpResult;
                }
            } else if(startY == bally) {
                if(startX < ballx) {
                    res4 = tmpResult;
                } else {
                    res3 = tmpResult;
                }
            }
            System.out.println(res1+" "+res2+" "+res3+" "+res4);
            tmpResult = Math.min(tmpResult, Math.min(res1, Math.min(res2, Math.min(res3, res4))));
            
            list.add(tmpResult);
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}