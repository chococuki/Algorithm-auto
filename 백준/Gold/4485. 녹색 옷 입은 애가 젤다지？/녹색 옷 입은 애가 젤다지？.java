import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int row;
		int col;
		int rupy;
		
		public Node(int row, int col, int rupy) {
			super();
			this.row = row;
			this.col = col;
			this.rupy = rupy;
		}

		@Override
		public int compareTo(Node o) {
			if(this.rupy==o.rupy) {
				return Integer.compare(o.col+o.row, this.col+this.row);
			}
			return Integer.compare(this.rupy, o.rupy);
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", rupy=" + rupy + "]";
		}
		
	}
	
	//최소 경로 탐색
	private static int findMinRoute(int[][] board) {
		int N = board.length;
		//해당 위치까지 이동하는 최소값
		int[][] minVal = new int[N][N];
		
		//최대값으로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(minVal[i], Integer.MAX_VALUE);
		}
		
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		//가장 빠른 경로를 위한 pq
		PriorityQueue<Node> minRoute = new PriorityQueue<>();
		minRoute.add(new Node(0, 0, board[0][0]));
		
		int result=0;
		
		breakPoint:
		while(true) {
			Node now = minRoute.poll();
			
			for (int i = 0; i < 4; i++) {
				int new_r = now.row + dr[i];
				int new_c = now.col + dc[i];
				
				//도착지점 도착시 종료
				if(new_r==N-1 && new_c==N-1) {
					result = now.rupy+board[new_r][new_c];
					break breakPoint;
				} else if(new_r>=0 && new_r<N && new_c>=0 
						&& new_c<N) {
					//해당 위치에 이전값보다 최소값으로 이동 가능할경우
					if(now.rupy+board[new_r][new_c] < minVal[new_r][new_c]) {
						minVal[new_r][new_c] = now.rupy+board[new_r][new_c];
						minRoute.add(new Node(new_r, new_c, now.rupy+board[new_r][new_c]));
					}
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int test_case = 0;
		while(true) {
			test_case++;
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			int[][] board = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("Problem "+test_case+": "+findMinRoute(board)+"\n");
		}
		
		System.out.println(sb.toString());
	}
}