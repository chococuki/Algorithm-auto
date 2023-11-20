import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for(int i = 0; i < skill.length; i++) {
            if(skill[i][0] == 1) {
                attack(sum, skill[i]);
            } else {
                heal(sum, skill[i]);
            }
        }
        
        updateBoard(sum, board);
        
        int answer = countAlive(board);
        return answer;
    }
    
    public void attack(int[][] sum, int[] skill) {
        sum[skill[1]][skill[2]] -= skill[5];
        sum[skill[1]][skill[4] + 1] += skill[5];
        sum[skill[3] + 1][skill[2]] += skill[5];
        sum[skill[3] + 1][skill[4] + 1] -= skill[5];
    }
    
    public void heal(int[][] sum, int[] skill) {
        sum[skill[1]][skill[2]] += skill[5];
        sum[skill[1]][skill[4] + 1] -= skill[5];
        sum[skill[3] + 1][skill[2]] -= skill[5];
        sum[skill[3] + 1][skill[4] + 1] += skill[5];
    }
    
    public void updateBoard(int[][] sum, int[][] board) {
        for(int j = 0; j < sum[0].length; j++) {
            int tmp = 0;
            for(int i = 0; i < sum.length; i++) {
                sum[i][j] += tmp;
                tmp = sum[i][j];
            }
        }
        
        for(int i = 0; i < board.length; i++) {
            int tmp = 0;
            for(int j = 0; j < board[0].length; j++) {
                tmp += sum[i][j];
                board[i][j] += tmp;
            }
        }
    }
    
    public int countAlive(int[][] board) {
        int count = 0;
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] >= 1) {
                    count++;
                }
            }
        }
        
        return count;
    }
}