import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min=Integer.MAX_VALUE;
	static List<Node> cctvs = new ArrayList<>();
	
	private static class Node {
		int x, y, type;

		public Node(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
	}
	
	private static void count(int[][] arr) {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) cnt++;
			}
		}
		min = Math.min(cnt, min);
//		print(arr);
//		System.out.println(cnt);
	}
	
	private static int[][] copy(int[][] arr){
		int[][] tmp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		
		return tmp;
	}
	
	private static void print(int[][] arr) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static int[][] draw(Node now, int[][] arr, int rot) {
		int[][][][] rotation = {{{{0},{1}},{{1},{0}},{{0},{-1}},{{-1},{0}}},
							    {{{0, 0},{1, -1}},{{1, -1},{0, 0}},{{},{}},{{},{}}},
							    {{{-1, 0},{0, 1}},{{0, 1},{1, 0}},{{1, 0},{0, -1}},{{0, -1},{-1, 0}}},
							    {{{0, -1, 0},{1, 0, -1}},{{-1, 0, 1},{0, 1, 0}},{{0, 1, 0},{1, 0, -1}},{{1, 0, -1},{0, -1, 0}}},
							    {{{0, 0, 1, -1},{1, -1, 0, 0}},{{},{}},{{},{}},{{},{}}}};
		
		int[] dx = rotation[now.type-1][rot][0];
		int[] dy = rotation[now.type-1][rot][1];
		
//		System.out.println(now.type);
		
		int new_x, new_y;
		for(int i=0; i<dx.length; i++) {
			new_x = now.x+dx[i];
			new_y = now.y+dy[i];
			
			while((new_x>=0 && new_x<N && new_y>=0 && new_y<M && arr[new_x][new_y]!=6)) {
				if(arr[new_x][new_y]==0) arr[new_x][new_y] = 8;
				new_x += dx[i];
				new_y += dy[i];
			}
		}
		
		return arr;
	}
	
	private static void find(int cnt, int[][] arr) {
		if(cnt==cctvs.size()) {
			count(arr);
			return;
		}
		
		Node now = cctvs.get(cnt);
		
		switch(now.type) {
		case 1:
			for(int r=0; r<4; r++) find(cnt+1, draw(now, copy(arr), r));
			break;
		case 2:
			for(int r=0; r<2; r++) find(cnt+1, draw(now, copy(arr), r));
			break;
		case 3:
			for(int r=0; r<4; r++) find(cnt+1, draw(now, copy(arr), r));
			break;
		case 4:
			for(int r=0; r<4; r++) find(cnt+1, draw(now, copy(arr), r));
			break;
		case 5:
			find(cnt+1, draw(now, copy(arr), 0));
			break;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		int tmp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				//cctv 위치 저장
				if(arr[i][j]!=0 && arr[i][j]!=6) cctvs.add(new Node(i, j, arr[i][j]));
			}
		}
		
		find(0, arr);
		
		System.out.println(min);
	}
}
