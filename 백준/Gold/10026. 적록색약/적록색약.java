import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static boolean[][] visited;
	
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
	
	private static int bfs(String[][] arr) {
		Queue<Node> que = new LinkedList<>();
		visited = new boolean[N][N];
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		int cnt=0;	//구역 갯수
		String color;	//시작 색깔
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					color = arr[i][j];
					que.add(new Node(i, j));
					cnt++;
					
					Node tmp=null;
					//하나의 구역 확인할때까지 반복
					while(!que.isEmpty()) {
						tmp = que.poll();
						
						int new_x, new_y;
						//주변 색 확인
						for(int k=0; k<4; k++) {
							new_x = tmp.x+dx[k];
							new_y = tmp.y+dy[k];
							
							if(new_x>=0 && new_x<N && new_y>=0 && new_y<N) {
								if(!visited[new_x][new_y] && arr[new_x][new_y].equals(color)) {
									que.add(new Node(new_x, new_y));
									visited[new_x][new_y] = true;
								}
							}
						}
						
					}
				}
			}
		}
		//구역 갯수 반환
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String[][] rgb = new String[N][N];
		String[][] rb = new String[N][N];
		
		String[] str;
		for(int i=0; i<N; i++) {
			str = br.readLine().split("");
			for(int j=0; j<N; j++) {
				rgb[i][j] = str[j];
				
				//적록 색약 배열에 G는 R로 바꾸어 저장
				if(str[j].equals("G")) rb[i][j] = "R";
				else rb[i][j] = str[j];
			}
		}
		
		System.out.println(bfs(rgb)+" "+bfs(rb));
	}
}