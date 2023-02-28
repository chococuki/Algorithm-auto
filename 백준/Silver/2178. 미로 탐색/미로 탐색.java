import java.io.*;
import java.util.*;

public class Main {	
	static int N;
	static int M;
	static int dept;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean visit[][];
	static int board[][];
	static Queue<Node> que = new LinkedList<>();
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void dfs(int x, int y) {
		int now_x, now_y;
		
		que.add(new Node(x, y));
		visit[x][y] = true;
		
		while (!que.isEmpty()) {
			Node node = que.poll();
			
			for(int i=0; i<4; i++) {
				now_x = node.x + dx[i];
				now_y = node.y + dy[i];
				
				if(now_x >= 1 && now_x <= N && now_y >= 1 && now_y <= M &&
						!visit[now_x][now_y] && board[now_x][now_y]==1) {
					que.add(new Node(now_x, now_y));
					visit[now_x][now_y] = true;
					board[now_x][now_y] = board[node.x][node.y]+1;
					if(visit[N][M]) {
						return;
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		board = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			str = br.readLine().split("");
			for(int j=1; j<=M; j++) {
				board[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		visit = new boolean[N+1][M+1];
		visit[1][1] = true;
		dfs(1, 1);
		
		System.out.println(board[N][M]);
		
	}
}
