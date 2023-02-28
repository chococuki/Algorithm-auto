import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, result;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	private static void bfs(int high) {
		Queue<Node> que = new LinkedList<>();
		visited = new boolean[N][N];
		int new_x, new_y, cnt=0;
		Node tmp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]>high && !visited[i][j]) {
					que.add(new Node(i, j));
					visited[i][j] = true;
					cnt++;
					
					while(!que.isEmpty()) {
						tmp = que.poll();
						for(int k=0; k<4; k++) {
							new_x = tmp.x+dx[k];
							new_y = tmp.y+dy[k];
							
							if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && !visited[new_x][new_y]) {
								visited[new_x][new_y] = true;
								
								if(arr[new_x][new_y] > high) {
									que.add(new Node(new_x, new_y));
								}
							}
						}
					}
					
				}
			}
		}
		
		result = Math.max(result, cnt);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		//배열 초기화
		arr = new int[N][N];
		
		//배열 생성
		int maxhigh=0, minhigh=Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxhigh = Math.max(maxhigh, arr[i][j]);
				minhigh = Math.min(minhigh, arr[i][j]);
			}
		}
		
		for(int high=minhigh-1; high<=maxhigh; high++) {
			bfs(high);
		}
		
		System.out.println(result);
	}
}