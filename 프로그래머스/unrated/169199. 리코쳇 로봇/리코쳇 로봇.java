import java.util.*;

class Solution {
    static int R, C;
    static char[][] map;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static PriorityQueue<Position> pq = new PriorityQueue<>();
    
    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        
        map = new char[R][C];
        
        for(int i = 0; i < R; i++){
            map[i] = board[i].toCharArray();
        }
        
        
        
        bp:
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'R') {
                    pq.add(new Position(i, j, 0));
                    map[i][j] = '.';
                    break bp;
                }
            }
        }
        
        int answer = -1;
        boolean[][] visited = new boolean[R][C];
        end:
        while(!pq.isEmpty()) {
            Position now = pq.poll();
            for(int i = 0; i < 4; i++) {
                int row = now.row + dr[i];
                int col = now.col + dc[i];
                if(row<0 || row>=R || col<0 || col>=C) continue;
                if(row>=0 && row<R && col>=0 && col<C && map[row][col]=='D') continue;
                
                while(true) {
                    row += dr[i];
                    col += dc[i];
                    
                    if(row>=0 && row<R && col>=0 && col<C) {
                        if(map[row][col]=='D') {
                            break;
                        }
                    } else if(row<0 || row>=R || col<0 || col>=C) {
                        break;
                    }
                }
                
                row -= dr[i];
                col -= dc[i];
                if(map[row][col] == 'G') {
                    answer = now.cnt + 1;
                    break end;
                } else if(!visited[row][col]){
                    visited[row][col] = true;
                    pq.add(new Position(row, col, now.cnt+1));
                }
            }
        }

        return answer;
    }
}

class Position implements Comparable<Position> {
    int row;
    int col;
    int cnt;
    
    public Position (int row, int col, int cnt) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
    
    public int compareTo(Position o) {
        return this.cnt - o.cnt;
    }
}