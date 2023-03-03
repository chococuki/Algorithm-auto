import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static List<Node> robot	= new ArrayList<>();
	static Queue<Node> dust = new LinkedList<>();
	
	static class Node {
		int x, y, val;

		public Node(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", val=" + val + "]";
		}
		
	}
	
	//초기화 함수
	private static void make() {
		board = new int[R+2][C+2];
		
		//테두리 벽 설정
		for (int i = 0; i <= R+1; i++) {
			board[i][0]=-1;
			board[i][C+1]=-1;
		}
		for (int i = 0; i <= C+1; i++) {
			board[0][i]=-1;
			board[R+1][i]=-1;
		}
	}
	
	//시작 함수
	private static void start() {
		for (int i = 0; i < T; i++) {
			spread();
			air();
		}
	}
	
	//먼지 퍼트리는 함수
	private static void spread() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(board[i][j]!=0 && board[i][j]!=-1) {
					dust.add(new Node(i, j, board[i][j]));
					board[i][j] = 0;
				}
			}
		}
		
		while(!dust.isEmpty()) {
			Node now = dust.poll();
			int spreadVal = now.val/5;
			
			int cnt=0, new_x, new_y;
			for (int i = 0; i < 4; i++) {
				new_x = now.x+dx[i];
				new_y = now.y+dy[i];
				
				if(board[new_x][new_y]!=-1) {
					cnt++;
					board[new_x][new_y] += spreadVal;
				}
			}
			board[now.x][now.y] += now.val-(spreadVal*cnt);
		}
	}
	
	//공기 순환
	private static void air() {
		int mode = 3, new_x, new_y;
		//top
		int now_x = robot.get(0).x;
		int now_y = robot.get(0).y;
		now_x--;
		while(true) {
			new_x = now_x+dx[mode];
			new_y = now_y+dy[mode];
			if(board[new_x][new_y]==-1 || new_x>robot.get(0).x) {
				if(mode==2) break;
				else if(mode==1) mode=2;
				else if(mode==0) mode=1;
				else if(mode==3) mode=0;
				
				new_x = now_x+dx[mode];
				new_y = now_y+dy[mode];
			}
			board[now_x][now_y] = board[new_x][new_y];
			now_x = new_x;
			now_y = new_y;
		}
		board[now_x][now_y] = 0;
		
		//bottom
		now_x = robot.get(1).x;
		now_y = robot.get(1).y;
		now_x++;
		mode = 1;
		while(true) {
			new_x = now_x+dx[mode];
			new_y = now_y+dy[mode];
			if(board[new_x][new_y]==-1 || new_x<robot.get(1).x) {
				if(mode==2) break;
				else if(mode==3) mode=2;
				else if(mode==0) mode=3;
				else if(mode==1) mode=0;
				
				new_x = now_x+dx[mode];
				new_y = now_y+dy[mode];
			}
			board[now_x][now_y] = board[new_x][new_y];
			now_x = new_x;
			now_y = new_y;
		}
		board[now_x][now_y] = 0;
		
	}

	private static int count() {
		int cnt=0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(board[i][j]!=-1) cnt+=board[i][j];
			}
		}
		return cnt;
	}
	
	private static void print() {
		for (int i = 0; i <= R+1; i++) {
			for (int j = 0; j <= C+1; j++) {
				System.out.print(board[i][j]+"\t");
			}System.out.println();
		}System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		make();
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j]==-1) robot.add(new Node(i, j, -1));
			}
		}
		
		start();
		
		System.out.println(count());
	}
}