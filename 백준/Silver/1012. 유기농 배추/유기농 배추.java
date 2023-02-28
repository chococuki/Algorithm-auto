import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int M, N, cnt;
	static int[][] arr;
	
	public static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static void find() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==1) bfs(i, j);
			}
		}
	}
	
	private static void bfs(int i, int j) {
		arr[i][j] = 0;
		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(i, j));
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		int x=0, y=0;
		Node node = null;
		while(!que.isEmpty()) {
			node = que.poll();
			x = node.x;
			y = node.y;
			
			int new_x=0, new_y=0;
			for(int k=0; k<4; k++) {
				new_x = x+dx[k];
				new_y = y+dy[k];
				
				if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && arr[new_x][new_y]==1) {
					que.offer(new Node(new_x, new_y));
					arr[new_x][new_y] = 0;
				}
			}
		}
		cnt++;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			cnt=0;
			str = br.readLine().split(" ");
			
			M = Integer.parseInt(str[0]);
			N = Integer.parseInt(str[1]);
			int K = Integer.parseInt(str[2]);
			
			arr = new int[N][M];
			
			for(int i=0; i<K; i++) {
				str = br.readLine().split(" ");
	
				arr[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = 1;
			}
			
			find();
			
			System.out.println(cnt);
			
		}
		
	}
}