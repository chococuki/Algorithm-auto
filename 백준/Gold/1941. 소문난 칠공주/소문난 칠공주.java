import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int result;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] table = new char[5][5];
	static int[][] pick = new int[5][5];
	
	static class Position {
		int row;
		int col;
		
		public Position(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static void start(int index, int cnt) {
		if(cnt == 7) {
			if(check()) {
				result++;
			}
			return;
		}
		
		for (int i = index; i < 25; i++) {
			int r = i/5;
			int c = i%5;
			
			pick[r][c] = 1;
			start(i+1, cnt+1);
			pick[r][c] = 0;
		}
	}
	
	private static boolean check() {
		boolean[][] visited = new boolean[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(pick[i][j] == 1) {
					Queue<Position> que = new LinkedList<>();
					visited[i][j] = true;
					que.add(new Position(i, j));
					int cnt = 0;
					int cntS = (table[i][j]=='S' ? 1 : 0);
					while(!que.isEmpty()) {
						cnt++;
						Position now = que.poll();
						
						for (int k = 0; k < 4; k++) {
							int row = now.row + dr[k];
							int col = now.col + dc[k];
							
							if(row>=0 && row<5 && col>=0 && col<5 
									&& pick[row][col]==1 && !visited[row][col]) {
								visited[row][col] = true;
								que.add(new Position(row, col));
								if(table[row][col] == 'S') {
									cntS++;
								}
							}
						}
					}
					
					if(cnt == 7 && cntS >= 4) return true;
					else return false;
				}
			}
		}
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int r = 0; r < 5; r++) {
			String str = br.readLine();
			for (int c = 0; c < 5; c++) {
				table[r][c] = str.charAt(c);
			}
		}
		
		start(0, 0);
		
		System.out.println(result);
	}
}