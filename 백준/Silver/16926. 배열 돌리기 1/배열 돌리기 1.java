import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static String[][] arr;
	static boolean[][] isrotate;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	private static void rotate(int start) {
//		System.out.println("start "+start);
		int now_x = start;
		int now_y = start;
		String tmp = arr[start][start];
		int mode = 0;
//		System.out.println("---");
		isrotate[now_x][now_y] = true;
		int new_x, new_y;
		do {
			if(now_x+dx[mode] <= N && now_x+dx[mode] > 0 && now_y+dy[mode] <= M && now_y+dy[mode] > 0 && !isrotate[now_x+dx[mode]][now_y+dy[mode]]) {
				new_x = now_x+dx[mode];
				new_y = now_y+dy[mode];
			} else {
				mode += 1;
				if(mode==4) mode=0;
				new_x = now_x+dx[mode];
				new_y = now_y+dy[mode];
			}
//			System.out.println(arr[now_x][now_y]+" <- "+arr[new_x][new_y]);
			arr[now_x][now_y] = arr[new_x][new_y];
			isrotate[now_x][now_y] = true;
			now_x = new_x;
			now_y = new_y;
		} while(!((new_x-1)== start && new_y == start));
		arr[new_x][new_y] = tmp;
//		System.out.println("-----");
		if(start<(N/2) && start<(M/2)) rotate(start+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new String[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				arr[i][j] = st.nextToken();
			}
		}
		
		for(int i=0; i<R; i++) {
//			System.out.println(i);
			isrotate = new boolean[N+1][M+1];
			rotate(1);
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
}
