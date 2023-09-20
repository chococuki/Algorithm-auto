import java.util.*;

class Solution {
    public static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        boolean[] visited = new boolean[dungeons.length];
        
        explore(visited, dungeons, k, 0);
        
        return answer;
    }
    
    public void explore(boolean[] visited, int[][] dungeons, int k, int count) {
        answer = Math.max(count, answer);
        
        for(int i = 0;  i < dungeons.length; i++) {
            if(!visited[i]) {
                if(k >= dungeons[i][0]) {
                    visited[i] = true;
                    k -= dungeons[i][1];
                    
                    explore(visited, dungeons, k, count + 1);
                    
                    k += dungeons[i][1];
                    visited[i] = false;
                }
            }
        }
    }
    
}