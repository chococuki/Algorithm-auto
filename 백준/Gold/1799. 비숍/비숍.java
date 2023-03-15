import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] result = new int[2];
	static int[][] canput, board;
	static boolean[][] colors;
	static int[] dx = {-1, -1};
	static int[] dy = {-1, 1};
	
	private static void dfs(int start, int cnt, boolean black) {
		result[black ? 1 : 0] = Math.max(result[black ? 1 : 0], cnt);
		
		for (int k = start; k < N*N; k++) {
			int i = k/N;
			int j = k%N;
			
			//조건에 부합하지 않으면 pass
			if(colors[i][j] != black || canput[i][j] == 0 || !check(i, j)) continue;
				
			board[i][j] = 1;
			dfs(k+1, cnt+1, black);
			board[i][j] = 0;
			
		}
	}
	
	
	//상단 대각선 방향에 비숍이 있는지 확인
	//상단부터 채우기 때문에 하단 확인은 불필요
	private static boolean check(int x, int y){
		
		for (int i = 0; i < 2; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			while(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(board[nx][ny]==1) {
					return false;
				}
				nx += dx[i];
				ny += dy[i];
			}
		}
		return true;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		canput = new int[N][N];
		board = new int[N][N];
		colors = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				canput[i][j] = Integer.parseInt(st.nextToken());
				colors[i][j] = (i%2==0 && j%2==0) || (i%2!=0 && j%2!=0);
			}
		}
		
		//체스판의 흰,검은 부분을 나누어 계산
		dfs(0, 0, true);
		dfs(0, 0, false);
		
		System.out.println(result[0]+result[1]);
	}
}