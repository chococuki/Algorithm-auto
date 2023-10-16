import java.util.*;

class Solution {
    public boolean[][] visited;
    public int[][] map;
    public int M, N;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        M = m;
        N = n;
        map = picture;
        visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }
                
                if(!visited[i][j]) {
                    numberOfArea++;
                    visited[i][j] = true;
                    maxSizeOfOneArea = Math.max(bfs(i, j), maxSizeOfOneArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int r, int c) {
        int color = map[r][c];
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(r, c));
        
        int count = 1;
        while(!que.isEmpty()) {
            Node node = que.poll();
            
            for(int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];
                
                if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc]) {
                    continue;
                }
                
                if(map[nr][nc] == color) {
                    count++;
                    visited[nr][nc] = true;
                    que.add(new Node(nr, nc));
                }
            }
        }
        
        return count;
    }
}

class Node {
    int r;
    int c;
    
    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}