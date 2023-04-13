import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        int R = maps.length;
        int C = maps[0].length();
        
        char[][] map = new char[R][C];
        PriorityQueue<Position> pq = new PriorityQueue<>();
        
        for(int i = 0; i < R; i++) {
            map[i] = maps[i].toCharArray();
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'S') pq.add(new Position(i, j, 0, 0));
            }
        }
        
        //레버 당기기 전/후 -> 3차월 방문 배열
        boolean[][][] visited = new boolean[R][C][2];
        
        int answer = -1;
        all:
        while(!pq.isEmpty()) {
            Position now = pq.poll();
            
            for(int i = 0; i < 4; i++) {
                int row = now.row + dr[i];
                int col = now.col + dc[i];
                
                if(row>=0 && row<R && col>=0 && col<C) {
                    if(!visited[row][col][now.lever]) {
                        visited[row][col][now.lever] = true;
                        
                        //벽
                        if(map[row][col] == 'X') continue;
                        
                        //레버 
                        if(map[row][col] == 'L') {
                            visited[row][col][1] = true;
                            pq.add(new Position(row, col, now.cnt+1, 1));
                            continue;
                        //출구
                        } else if(map[row][col] == 'E') {
                            //레버를 당긴 후
                            if(now.lever == 1) {
                                answer = now.cnt+1;
                                break all;
                            }
                        }
                        //통로, 시작점
                        pq.add(new Position(row, col, now.cnt+1, now.lever));
                    }
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
    int lever;
    
    public Position(int row, int col, int cnt, int lever) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
        this.lever = lever;
    }
    
    public int compareTo(Position p) {
        if(this.lever == p.lever)
            return this.cnt - p.cnt;
        return p.lever - this.lever;
    }
}