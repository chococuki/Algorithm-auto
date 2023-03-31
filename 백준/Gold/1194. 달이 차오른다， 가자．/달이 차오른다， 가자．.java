import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
	static int mazeRow, mazeCol;
	static char[][] maze;
	
	//현재 위치에 대한 정보
	static class Position implements Comparable<Position> {
		int row;
		int col;
		int dept;
		HashSet<Character> key;
		
		public Position(int row, int col, int dept) {
			super();
			this.row = row;
			this.col = col;
			this.dept = dept;
			this.key = new HashSet<Character>();
		}

		public Position(int row, int col, int dept, HashSet<Character> key) {
			super();
			this.row = row;
			this.col = col;
			this.dept = dept;
			this.key = key;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.dept, o.dept);
		}

		@Override
		public String toString() {
			return "Position [row=" + row + ", col=" + col + ", dept=" + dept + ", key=" + key + "]";
		}

	}
	
	//열쇠 보유에 따른 visited를 위한 클래스
	static class Visit {
		Map<HashSet<Character>, Boolean> visited;

		public Visit() {
			this.visited = new LinkedHashMap<>();
		}
		
	}
	
	private static int startMove(Position start) {
		PriorityQueue<Position> fastRoute = new PriorityQueue<>();
		fastRoute.add(start);
		
		//현위치에서 이동가능한 위치
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		//각 칸에 대한 visited 처리를 위한 배열
		Visit[][] visit = new Visit[mazeRow][mazeCol];
		for (int r = 0; r < mazeRow; r++) {
			for (int c = 0; c < mazeCol; c++) {
				visit[r][c] = new Visit();
			}
		}
		
		visit[start.row][start.col].visited.put(start.key, true);
		
		int result = -1;
		breakPoint:
		while(!fastRoute.isEmpty()) {
			Position now = fastRoute.poll();
			
			for (int i = 0; i < 4; i++) {
				int new_r = now.row + dr[i];
				int new_c = now.col + dc[i];
				
				//확인 하려는 칸이 미로 내부에 있는지 && 가지고 있는 key set으로 해당 칸을 방문한적이 없는지
				if(checkOnMaze(new_r, new_c) && !visit[new_r][new_c].visited.containsKey(now.key)) {
					visit[new_r][new_c].visited.put(now.key, true);
					
					char cur = maze[new_r][new_c];
					if(maze[new_r][new_c] == '1') {
						result = now.dept+1;
						break breakPoint;
					} else if(maze[new_r][new_c] == '#') {
						continue;
					} else if(maze[new_r][new_c] == '.') {
						fastRoute.add(new Position(new_r, new_c, now.dept+1, new HashSet<>(now.key)));
					} else if(cur >= 'a' && cur <= 'f') {
						HashSet<Character> tmp = new HashSet<>(now.key);
						tmp.add(cur);
						fastRoute.add(new Position(new_r, new_c, now.dept+1, tmp));
					} else if(cur >= 'A' && cur <= 'F') {
		                char key = (char) (cur + 32);
		                if (!now.key.contains(key)) {
		                    continue;
		                } else {
		                    fastRoute.add(new Position(new_r, new_c, now.dept + 1, now.key));
		                }
					}
				}
			}
		}
		
		return result;
	}
	
	//미로 내부에 있는지 확인하는 함수
	private static boolean checkOnMaze(int row, int col) {
		if(row>=0 && row<mazeRow && col>=0 && col<mazeCol) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		mazeRow = Integer.parseInt(str[0]);
		mazeCol = Integer.parseInt(str[1]);
		
		maze = new char[mazeRow][mazeCol];
		
		Position start = null;
		for (int r = 0; r < mazeRow; r++) {
			String s = br.readLine();
			for (int c = 0; c < mazeCol; c++) {
				maze[r][c] = s.charAt(c);
				if(maze[r][c] == '0') {
					maze[r][c] = '.';
					start = new Position(r, c, 0);
				}
			}
		}
		
		System.out.println(startMove(start));		
	}
}