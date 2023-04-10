import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, result;
	static int[][] board, dp;
	static boolean infinite;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	private static void move(int row, int col, int cnt) {
		int weight = board[row][col];
		
		dp[row][col] = cnt;
		if(cnt > result) {
			result = cnt;
		}
		
		for (int i = 0; i < 4; i++) {
			int nrow = row + dr[i] * weight;
			int ncol = col + dc[i] * weight;
			
			if(nrow<0 || nrow>=N || ncol<0 || ncol>=M) {
				continue;
			}
			
			if(board[nrow][ncol] == -1) {
				continue;
			}
			
			if(dp[nrow][ncol] > cnt) {
				continue;
			}
			
			if(visited[nrow][ncol]) {
				infinite = true;
				return;
			}
			
			visited[nrow][ncol] = true;
			move(nrow, ncol, cnt+1);
			visited[nrow][ncol] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		visited = new boolean[N][M];
		dp = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int m = 0; m < M; m++) {
				char c = str.charAt(m);
				
				if(c == 'H') board[n][m] = -1;
				else board[n][m] = c - '0';
			}
		}
		
		visited[0][0] = true;
		move(0, 0, 1);
		
		System.out.println(infinite ? -1 : result);
	}
}