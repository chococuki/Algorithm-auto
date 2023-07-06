import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        
        boolean[][] board = new boolean[park.length][park[0].length()];
        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j < park[0].length(); j++) {
                char s = park[i].charAt(j);
                if(s == 'S') {
                    answer[0] = i;
                    answer[1] = j;
                } else if(s == 'X') {
                    board[i][j] = true;
                }
            }
        }
        
        int moveX = 0;
        int moveY = 0;
        StringTokenizer st;
        for(String route : routes) {
            st = new StringTokenizer(route);
            String pos = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            
            if(pos.equals("N")) {
                moveX = -1;
                moveY = 0;
            } else if(pos.equals("S")) {
                moveX = 1;
                moveY = 0;
            } else if(pos.equals("W")) {
                moveX = 0;
                moveY = -1;
            } else if(pos.equals("E")) {
                moveX = 0;
                moveY = 1;
            }
            
            for(int i = 1; i <= n; i++) {
                int nowX = answer[0] + moveX * i;
                int nowY = answer[1] + moveY * i;
                
                if(nowX < 0 || nowX >= board.length || nowY < 0 || nowY >= board[0].length) {
                    break;
                }
                if(board[nowX][nowY]) {
                    break;
                }
                if(i == n) {
                    answer[0] += moveX * n;
                    answer[1] += moveY * n;
                }
            }
        }
        
        
        
        return answer;
    }
}