import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static String[] in;
	static String[][] map;
	static int[][] dp; // 얘는 시간복잡도를 줄이기 위한 경로 갯수 저장배열
	static boolean[][] visited;
	static boolean[][] finish;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result = 1;
	static boolean isCycle = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new String[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		finish = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().split("");
		}
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1);
		}
		
		visited[0][0] = true;
		dfs(0, 0, 1);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				result = Math.max(result, dp[i][j]);
//			}
//		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for (int k = 0; k < N; k++) {
			for (int p = 0; p < M; p++) {
				result = Math.max(result, dp[k][p]);
			}
		}
		
		if(isCycle==false)System.out.println(result);
		else System.out.println(-1);
	}

	public static void dfs(int x, int y, int cnt) {

		// 얼마나 이동할지
		int weight = Integer.parseInt(map[x][y]);
		dp[x][y] = cnt;

		if(cnt>result) {
			result = cnt;
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println("----------------------");
//
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("----------------------");

		// 4방향에 대해서 이동한다
		for (int i = 0; i < 4; i++) {
			int nx = x + weight * dx[i];
			int ny = y + weight * dy[i];

			// 범위 벗어나는 경우나 구멍에 빠지는 경우 continue 해버리기
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny].equals("H"))
				continue;
					
			// 만약 dfs를 도는데 visited가 true이면 싸이클 발생
			if (visited[nx][ny] == true) {
				isCycle = true;
				return;
			}
			
			// 만약 다음 경로의 DP값이 현재의 DP값 보다 크다면 탐색할 이유가 없다 -> 다른 경로로 들어왔을 때 더 크니까
			if (cnt<dp[nx][ny]) {				
				continue;
			}
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt + 1);	
			visited[nx][ny] = false;
		}
	}

}