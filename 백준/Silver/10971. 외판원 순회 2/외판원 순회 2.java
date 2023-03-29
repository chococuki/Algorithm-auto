import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, prices[][];
	static int result = Integer.MAX_VALUE;
	static boolean visited[];
	
	private static void move(int start, int from, int cnt, int price) {
		//출발지 정하기
		if(start==-1) {
			for (int i = 0; i < N; i++) {
				move(i, i, cnt, price);
			}
			return;
		}
		
		if(cnt==N) {
			//종착지가 시작지점일때
			if(start==from) result = Math.min(price, result);
			return;
		}
		
		//다음 방문지 
		for (int to = 0; to < N; to++) {
			if (!visited[to] && prices[from][to]!=0) {
				visited[to] = true;
				move(start, to, cnt+1, price+prices[from][to]);
				visited[to] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		prices = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				prices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(-1, -1, 0, 0);
		
		System.out.println(result);
	}
}