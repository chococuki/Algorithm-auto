import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int count;
	private static int time;
	private static int remain;
	private static LinkedList<Pair> q;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		count = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					count++;
				}
			}
		}
		time = 0;
		remain = 0;
		visited = new boolean[N][M];
		visit();
		while(true) {
			check();
			remain = count;
			remove();
			time++;
			if(count == 0) {
				break;
			}
			visit();

		}
		System.out.println(time);
		System.out.println(remain);
	}

	static void remove() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 2) {
					count--;
					visited[i][j] = true;
					map[i][j] = 0; 
				}
			}
		}
	}
	static void check() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] >0) {
					for(int dir=0;dir<4;dir++) {
						int nx = i+dx[dir];
						int ny = j+dy[dir];
						if(visited[nx][ny]) {
							map[i][j] = 2;
							break;
						}
					}
				}
			}
		}
	}
	static void visit() {
		q = new LinkedList<Pair>();
		visited = new boolean[N][M];
		q.add(new Pair(0,0));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			for(int i=0;i<4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx<0 || nx > N-1 || ny <0 || ny >M-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new Pair(nx,ny));
				}
			}

		}

	}
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}