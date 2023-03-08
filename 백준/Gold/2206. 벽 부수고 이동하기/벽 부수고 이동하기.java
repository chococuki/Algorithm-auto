import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, M;
	static int[][] board;
	static int[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	//위치, 벽 부숨 여부
	static class Node implements Comparable<Node>{
		int x, y, cnt;
		boolean isbreak;
		
		public Node(int x, int y, int cnt, boolean isbreak) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isbreak = isbreak;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
	
	//bfs
	private static int find() {
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			//도착지에 도착했을때
			if(now.x==N && now.y==M) return now.cnt; 
			
			for (int i = 0; i < 4; i++) {
				int x = now.x+dx[i];
				int y = now.y+dy[i];
				
				if(x>0 && x<=N && y>0 && y<=M) {
					if(visited[x][y]==0) {
						// visited:2 -> 임시 방문 처리, 1 -> 방문
						if(now.isbreak) visited[x][y] = 2;
						else visited[x][y] = 1;
						
						//벽을 만나고, 벽부수지 않았을때
						if(board[x][y]==1 && !now.isbreak) {
							pq.add(new Node(x, y, now.cnt+1, true));
						//길을 만났을때
						} else if(board[x][y]==0) {
							pq.add(new Node(x, y, now.cnt+1, now.isbreak));
						}
					} else if(visited[x][y]==2 && !now.isbreak){
						visited[x][y] = 1;
						
						//벽을 만났을때
						if(board[x][y]==1) {
							pq.add(new Node(x, y, now.cnt+1, true));
						//길을 만났을때
						} else if(board[x][y]==0) {
							pq.add(new Node(x, y, now.cnt+1, now.isbreak));
						}
					}
				}
			}
		}
		
		//경로 없을때 
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		board = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			str = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		visited[1][1] = 1;
		pq.offer(new Node(1, 1, 1, false));
		int result = find();
		
		System.out.println(result);
	}
}