import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, mode=0;
	static int[][] arr;
	static int[] dx = {0, 1, 0, -1};	//우, 하, 좌, 상
	static int[] dy = {1, 0, -1, 0};
	static Deque<Node> snake = new LinkedList<>();
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	//초기화 함수
	private static void make() {
		arr = new int[N+2][N+2];
		for(int i=0; i<N+2; i++) {
			arr[0][i]=9;
			arr[N+1][i]=9;
			arr[i][0]=9;
			arr[i][N+1]=9;
		}
		snake.add(new Node(1, 1));
		arr[1][1]=8;
	}
	
	//뱀 이동 함수
	private static boolean move() {
		Node now = snake.peekLast();
		int new_x = now.x+dx[mode];
		int new_y = now.y+dy[mode];
		
		if(arr[new_x][new_y]==8 || arr[new_x][new_y]==9) return false;
		
		//사과를 만나지 않으면 꼬리 이동
		if(arr[new_x][new_y]!=1) {
			Node tail = snake.pollFirst();
			arr[tail.x][tail.y] = 0;
		}
		
		//머리 이동
		snake.offerLast(new Node(new_x, new_y));
		//뱀이 있는 위치 8로 저장
		arr[new_x][new_y]=8;
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		make();
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			//사과의 위치를 1로 저장
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		int sec=0, time=0;
		String rot;
		boolean isbreak = false;
		moveSnake:
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			sec = Integer.parseInt(st.nextToken());
			
			while(time!=sec) {
				time++;
				if(!move()) {
					isbreak = true;
					break moveSnake;
				};
			}
			
			rot = st.nextToken();
			if(rot.equals("D")) {
				if(++mode==4) mode=0;
			} else {
				if(--mode==-1) mode=3;
			}
			
		}
		
		//게임오버 아니면 계속 이동
		while(!isbreak) {
			time++;
			if(!move()) {
				break;
			}
		}
		
		System.out.println(time);
		
	}
}