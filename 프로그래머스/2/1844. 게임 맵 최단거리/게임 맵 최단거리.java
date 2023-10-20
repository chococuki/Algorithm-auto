import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 1));
        visited[0][0] = true;
        
        int answer = -1;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(node.r == n - 1 && node.c == m - 1) {
                answer = node.d;
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if(visited[nr][nc] || maps[nr][nc] == 0) {
                    continue;
                }
                
                visited[nr][nc] = true;
                pq.add(new Node(nr, nc, node.d + 1));
            }
        }

        return answer;
    }
}

class Node implements Comparable<Node> {
    int r;
    int c;
    int d;
    
    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
    
    public int compareTo(Node n) {
        return Integer.compare(this.d, n.d);
    }
}