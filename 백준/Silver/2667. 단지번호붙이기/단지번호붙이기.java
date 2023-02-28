import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N;
	static int[][] arr;
	static List<Integer> list = new ArrayList<Integer>();
	
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
			for(int j=0; j<N; j++) {
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
		
		int x=0, y=0, count=1;
		Node node = null;
		while(!que.isEmpty()) {
			node = que.poll();
			x = node.x;
			y = node.y;
			
			int new_x=0, new_y=0;
			for(int k=0; k<4; k++) {
				new_x = x+dx[k];
				new_y = y+dy[k];
				
				if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && arr[new_x][new_y]==1) {
					que.offer(new Node(new_x, new_y));
					arr[new_x][new_y] = 0;
					count++;
				}
			}
		}
		list.add(count);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		String[] str;
		for(int i=0; i<N; i++) {
			str = br.readLine().split("");
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		find();
		
		System.out.println(list.size());
		
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
}
