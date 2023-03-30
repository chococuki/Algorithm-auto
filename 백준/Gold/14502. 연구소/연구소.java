import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int row, col;
	static boolean visited[][];
	static Queue<Node> virus = new LinkedList<>();
	static List<Node> blank = new ArrayList<>();
	
	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	//빈공간에 벽 설치 후 안전구역 탐색
	private static int makeWall(int[][] board) {
		int result = 0;
		int tmp[][] = new int[row][col];
		
		
		for (int a = 0; a < blank.size(); a++) {
			for (int b = a+1; b < blank.size(); b++) {
				for (int c = b+1; c < blank.size(); c++) {
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < col; j++) {
							tmp[i][j] = board[i][j];
						}
					}
					tmp[blank.get(a).row][blank.get(a).col] = 1;
					tmp[blank.get(b).row][blank.get(b).col] = 1;
					tmp[blank.get(c).row][blank.get(c).col] = 1;
					visited = new boolean[row][col];
					spredVirus(tmp);
					result = Math.max(countSafeZone(tmp), result);
					tmp[blank.get(c).row][blank.get(c).col] = 0;
				}
				
				tmp[blank.get(b).row][blank.get(b).col] = 0;
			}
			
			tmp[blank.get(a).row][blank.get(a).col] = 0;
		}
		
		return result;
	}
	
	//bfs로 바이러스 확산
	private static void spredVirus (int[][] board) {
		Queue<Node> tmp = new LinkedList<>(virus);
		
		while(!tmp.isEmpty()) {
			Node node = tmp.poll();
		
			int dr[] = {1, -1, 0, 0};
			int dc[] = {0, 0, 1, -1};
			
			for (int i = 0; i < 4; i++) {
				int new_r = node.row + dr[i];
				int new_c = node.col + dc[i];
				
				if(new_r>=0 && new_r<row && new_c>=0 && new_c<col && !visited[new_r][new_c] && board[new_r][new_c]==0) {
					visited[new_r][new_c] = true;
					board[new_r][new_c] = 2;
					tmp.add(new Node(new_r, new_c));
				}
			}
		}
	}
	
	// 안전구역 카운트 함수
	private static int countSafeZone(int[][] board) {
		int cnt = 0;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(board[i][j] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		int board[][] = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) virus.add(new Node(i, j));
				if(board[i][j] == 0) blank.add(new Node(i, j));
			}
		}
		
		System.out.println(makeWall(board));
	}
}