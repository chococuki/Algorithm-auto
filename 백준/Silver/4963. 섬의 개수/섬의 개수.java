import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static int w, h;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static Queue<Node> que = new LinkedList<>();
	
	public static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	
	private static int bfs() {
		int new_x, new_y, cnt=0;
		Node now;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(!visited[i][j] && arr[i][j]==1) {
					visited[i][j] = true;
					que.add(new Node(i, j));
					cnt++;
				}
				while(!que.isEmpty()) {
					now = que.poll();
					for(int k=0; k<8; k++) {
						new_x = now.x+dx[k];
						new_y = now.y+dy[k];
						
						if(new_x>=0 && new_x<h && new_y>=0 && new_y<w && !visited[new_x][new_y]) {
							visited[new_x][new_y] = true;
							if(arr[new_x][new_y]==1) {
								que.offer(new Node(new_x, new_y));
							}
							
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w==0 && h==0) break;
			
			arr = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = bfs();
			
			System.out.println(result);
		}
		
		
	}
}