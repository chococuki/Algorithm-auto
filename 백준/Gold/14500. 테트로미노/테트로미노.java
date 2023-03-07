import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, max;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	private static void start() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bfs(i, j, 0, 0);
				Tshape(i, j);
			}
		}
	}
	
	private static void bfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int new_x = x+dx[i];
			int new_y = y+dy[i];
			
			if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && !visited[new_x][new_y]) {
				visited[new_x][new_y] = true;
				bfs(new_x, new_y, cnt+1, sum+board[new_x][new_y]);
				visited[new_x][new_y] = false;
			}
		}
	}
	
	private static void Tshape(int x, int y) {
		int cnt=0, sum=board[x][y];
		
		for (int i = 0; i < 4; i++) {
			int new_x = x+dx[i];
			int new_y = y+dy[i];
			
			if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && !visited[new_x][new_y]) {
				cnt++;
				sum += board[new_x][new_y]; 
			}
		}
		
		if(cnt==3) {
			max = Math.max(max, sum);
		} else if(cnt==4) {
			for (int i = 0; i < 4; i++) {
				int new_x = x+dx[i];
				int new_y = y+dy[i];
				
				sum -= board[new_x][new_y];
				max = Math.max(max, sum);
				sum += board[new_x][new_y];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		max = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start();
		
		System.out.println(max);
	}
}
