import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int M, N;
	static int[][] board;
	static boolean[][] visit;
	static Queue<Node> que = new LinkedList<>();
	static Queue<Node> quetmp = new LinkedList<>();
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int find() {
		int day = 0;
		while(!que.isEmpty()) {
			day++;
			quetmp = new LinkedList<>(que);
			que = new LinkedList<>();
			int fin = quetmp.size();
			for(int i=0; i<fin; i++) {
				Node node = quetmp.poll();
				bfs(node);
			}
		}
		return day;
	}
	
	static void bfs(Node node) {
		int now_x, now_y;
		
		for(int i=0; i<4; i++) {
			now_x = node.x + dx[i];
			now_y = node.y + dy[i];
			
			if(now_x >= 0 && now_x < N && now_y >= 0 && now_y < M &&
					!visit[now_x][now_y] && board[now_x][now_y]==0) {
				que.add(new Node(now_x, now_y));
				visit[now_x][now_y] = true;
				board[now_x][now_y] = 1;
			}
		}
	}
	
	static boolean yummy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(board[i][j] == 0)
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		
		board = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(str[j]);
				
				if(str[j].equals("1")) {
					que.add(new Node(i, j));
					visit[i][j] = true;
				}
			}
		}
		int day = find();
		
		if(yummy())
			System.out.println(-1);
		else
			System.out.println(day-1);
	}
}
