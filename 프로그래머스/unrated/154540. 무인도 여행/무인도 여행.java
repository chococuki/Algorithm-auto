import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] maps) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        int[][] board = new int[maps.length][maps[0].length()];
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                char c = maps[i].charAt(j);
                
                if(c == 'X') {
                    visited[i][j] = true;
                    continue;
                } else {
                    board[i][j] = c - '0';
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        
        //bfs
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(visited[i][j]) {
                    continue;
                } else {
                    Queue<int[]> que = new LinkedList<>();
                    int[] tmp = {i, j};
                    que.add(tmp);
                    
                    int food = board[i][j];
                    visited[i][j] = true;
                    
                    while(!que.isEmpty()) {
                        int[] now = que.poll();
                        
                        for(int k = 0; k < 4; k++) {
                            int[] newArr = {now[0]+dx[k], now[1]+dy[k]};
                            
                            if(newArr[0] < 0 || newArr[0] >= board.length ||
                               newArr[1] < 0 || newArr[1] >= board[0].length) {
                                continue;
                            }
                            
                            if(visited[newArr[0]][newArr[1]]) {
                                continue;
                            } else {
                                visited[newArr[0]][newArr[1]] = true;
                            }
                            
                            food += board[newArr[0]][newArr[1]];
                            que.add(newArr);
                        }
                    }
                    
                    list.add(food);
                }
            }
        }
        
        Collections.sort(list);
        
        int[] answer;
        if(list.size() > 0) {
            answer = new int[list.size()];
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
        } else {
            answer  = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
}