import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] board;
	static Node[][] count;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Queue<Node> wall = new LinkedList<>();
	
	//좌표, (index, cnt) 저장을 위한 클래스
	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	//0인곳의 크기를 카운트 하여 count배열에 index와 cnt 저장
	private static void makeCount() {
		Queue<Node> que = new LinkedList<>();	//bfs를 위한 que
		Queue<Node> tmp = new LinkedList<>();	//빈칸수를 확인하고 해당칸에 cnt 저장을 위한 que
		
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 1;
				
				if(!visited[i][j] && board[i][j]==0) {
					visited[i][j] = true;
					que.add(new Node(i, j));
					tmp.add(new Node(i, j));
					index = count[i][j].x;	//시작값을 index로
					
					while(!que.isEmpty()) {
						Node now = que.poll();
						
						for (int k = 0; k < 4; k++) {
							int x = now.x + dx[k];
							int y = now.y + dy[k];
							
							if(x>=0 && x<N && y>=0 && y<M && !visited[x][y]) {
								visited[x][y] = true;
								if(board[x][y]==0) {
									cnt++;
									que.add(new Node(x, y));
									tmp.add(new Node(x, y));
								}
							}
						}
					}
					
					//cnt한 위치를 같은 index, cnt로 변경
					while(!tmp.isEmpty()) {
						Node node = tmp.poll();
						
						count[node.x][node.y] = new Node(index, cnt);
					}
				}
			}
		}
	}
	
	//벽 주변 을 확인
	private static void checkSide(Node node) {
		List<Integer> indexs = new ArrayList<>();
		
		int cnt = 1;
			
		for (int i = 0; i < 4; i++) {
			int x = node.x + dx[i];
			int y = node.y + dy[i];
			
			if(x>=0 && x<N && y>=0 && y<M) {
				if(board[x][y]==0 && !indexs.contains(count[x][y].x)) {
					indexs.add(count[x][y].x);
					cnt += count[x][y].y;
				}
			}
		}
		board[node.x][node.y] = cnt%10;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str;
		
		str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		board = new int[N][M];
		visited = new boolean[N][M];
		count = new Node[N][M];
		
		int index = 0;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(str[j]);
				count[i][j] = new Node(index++, 0);
				if(board[i][j]==1) {
					wall.add(new Node(i, j));
				}
			}
		}
		
		makeCount();
		
		while(!wall.isEmpty()) {
			checkSide(wall.poll());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(Integer.toString(board[i][j]));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}