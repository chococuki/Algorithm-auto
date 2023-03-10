import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] board, air;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Queue<Node> que = new LinkedList<>();
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	//시작
	private static int start() {
		int time = 0;
		while(check()) {
			time++;
			checkAirPlace();
			melt();
		}
		return time;
	}
	
	//치즈가 남았는지 확인
	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]==1) {
					return true;
				}
			}
		}
		return false;
	}
	
	//bfs - 외부 공기부분 찾는 함수
	private static void checkAirPlace() {
		boolean[][] visited = new boolean[N][M];
		air = new int[N][M];
		
		forbreak:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]==0) {
					que.add(new Node(i, j));
					visited[i][j] = true;
					air[i][j] = 1;
					break forbreak;
				}
			}
		}
		
		Node now;
		while(!que.isEmpty()) {
			now = que.poll();
			
			for (int i = 0; i < 4; i++) {
				Node n = new Node(now.x+dx[i], now.y+dy[i]);
				
				if(n.x>=0 && n.x<N && n.y>=0 && n.y<M && !visited[n.x][n.y]) {
					visited[n.x][n.y] = true;
					
					if(board[n.x][n.y]==0) {
						que.add(n);
						air[n.x][n.y] = 1;
					}
				}
			}
		}
	}
	
	//치즈 녹이는 함수
	private static void melt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]==1) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						if(air[i+dx[k]][j+dy[k]]==1) {
							cnt++;
							if(cnt==2) {	//두면 이상 만나면 녹음
								board[i][j] = 0;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		air = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = start();
		
		System.out.println(result);
	}
}