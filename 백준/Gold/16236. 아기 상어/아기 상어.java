import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt, time, movetime;
	static int[][] board;
	static boolean[][] visited;
	static Node shark;
	static PriorityQueue<Node> fish = new PriorityQueue<>();
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	static class Node implements Comparable<Node>{
		int x, y, size;

		public Node(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", size=" + size + "]";
		}
		
		
		//먹이를 규칙에 맞게 배열
		@Override
		public int compareTo(Node o) {
			if(this.x==o.x) return Integer.compare(this.y, o.y);
			return Integer.compare(this.x, o.x);
		}
		
	}
	
	//시작
	private static void start() {
		while(true) {
			if(search()) {
				time += movetime;
				cnt++;
				if(cnt==shark.size) {
					shark.size++;
					cnt=0;
				}
				board[shark.x][shark.y]=0;
				shark = new Node(fish.peek().x, fish.peek().y, shark.size);
				board[fish.peek().x][fish.peek().y]=9;
				fish = new PriorityQueue<>();
			} else {
				break;
			}
		}
	}
	
//	private static void print() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j]+" ");
//			}System.out.println();
//		}System.out.println();
//	}
	
	//거리별로 먹이 탐색
	private static boolean search() {
		PriorityQueue<Node> que = new PriorityQueue<>();
		PriorityQueue<Node> tmpQ;
		que.add(shark);
		visited = new boolean[N][N];
		visited[shark.x][shark.y]=true;
		
		Node node;
		movetime=0;
		while(fish.isEmpty()) {
			movetime++;
			tmpQ = new PriorityQueue<>(que);
			que = new PriorityQueue<>();
			while(!tmpQ.isEmpty()) {
				node = tmpQ.poll();
				
				for (int i = 0; i < 4; i++) {
					int new_x = node.x+dx[i];
					int new_y = node.y+dy[i];
					
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && !visited[new_x][new_y]){
						visited[new_x][new_y]=true;
						if(board[new_x][new_y]!=0 && board[new_x][new_y]<shark.size) {
							fish.add(new Node(new_x, new_y, board[new_x][new_y]));
						} else if(board[new_x][new_y]<=shark.size) {
							que.add(new Node(new_x, new_y, 0));
						}
					}
				}
			}
			if(que.isEmpty()) break;
		}
		return !fish.isEmpty();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
				if(board[i][j]==9) {
					shark = new Node(i, j, 2);
					board[i][j]=0;
				}
			}
		}
		
		start();
		
		System.out.println(time);
	}
}