import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static List<Integer> list = new LinkedList<>();
	
	
	public static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	//색칠하는 함수
	private static void draw(int startx, int starty, int endx, int endy) {
		for(int i=startx; i<endx; i++) {
			for(int j=starty; j<endy; j++) {
				arr[i][j] = 1;
			}
		}
	}
	
	//색칠 하지 않은곳 탐색
	private static void bfs() {
		Node tmp;
		int new_x, new_y;
		visited = new boolean[N][M];
		Queue<Node> que = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0 && !visited[i][j]) {
					visited[i][j] = true;
					que.add(new Node(i, j));
					
					int cnt=1;
					while(!que.isEmpty()) {
						tmp = que.poll();
						
						for(int k=0; k<4; k++) {
							new_x = tmp.x+dx[k];
							new_y = tmp.y+dy[k];
							
							if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && !visited[new_x][new_y]) {
								visited[new_x][new_y] = true;
								if(arr[new_x][new_y]==0) {
									que.add(new Node(new_x, new_y));
									cnt++;
								}
							}
						}
					}
					list.add(cnt);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		int startx, starty, endx, endy;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			startx = Integer.parseInt(st.nextToken());
			starty = Integer.parseInt(st.nextToken());
			endx = Integer.parseInt(st.nextToken());
			endy = Integer.parseInt(st.nextToken());
			
			draw(startx, starty, endx, endy);
		}
		
//		for(int i=0; i<M; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(arr[j][i]+ " ");
//			}
//			System.out.println();
//		}
		
		bfs();
		
		Collections.sort(list);
		
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}
}