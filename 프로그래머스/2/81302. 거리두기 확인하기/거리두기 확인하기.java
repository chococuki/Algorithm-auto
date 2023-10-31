import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        
        char[][] map;
        for(int p = 0; p < places.length; p++) {
            map = new char[5][5];
            
            for(int i = 0; i < 5; i++) {
                map[i] = places[p][i].toCharArray();
            }
            
            breakPoint:
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(map[i][j] == 'P') {
                        if(!check(map, i, j)) {
                            answer[p] = 0;
                            break breakPoint;
                        }
                    }
                }
            }
            
        }
        
        
        
        return answer;
    }
    
    public boolean check(char[][] map, int i, int j) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        boolean[][] visited = new boolean[5][5];
        visited[i][j] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(i, j, 0));
        
        boolean result = true;
        while(!que.isEmpty() && result) {
            Node node = que.poll();
            
            if(node.d >= 2) {
                continue;
            }
            
            for(int k = 0; k < 4; k++) {
                int nr = node.r + dr[k];
                int nc = node.c + dc[k];
                
                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                } else {
                    visited[nr][nc] = true;
                }
                
                if(map[nr][nc] == 'X') {
                    continue;
                } else if(map[nr][nc] == 'P') {
                    result = false;
                    break;
                } else {
                    que.add(new Node(nr, nc, node.d + 1));
                }
            }
        }
        
        return result;
    }
    
    public class Node {
        int r;
        int c;
        int d;
        
        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}

