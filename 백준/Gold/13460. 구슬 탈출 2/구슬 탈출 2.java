import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M;
	static Node node;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static HashSet<String> visited;
	static int result = -1;
	static Queue<Node> que;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		// 보드 생성
		node = new Node();
		node.map = new String[N][M];
		for(int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				node.map[i][j] =  str[j];
				
				if(str[j].equals("R")) {
					node.red = new Position(i, j);
				} else if(str[j].equals("B")) {
					node.blue = new Position(i, j);
				}
			}
		}
		
		// 같은 위치 저장 hash
		visited = new HashSet<>();
		visited.add(node.toString());
		
		// 가장 빠른 경로를 위한 que
		que = new LinkedList<>();
		que.add(node);
		while(result == -1 && !que.isEmpty()) {
			Node now = que.poll();
			move(now);
		}
		
		System.out.println(result);
	}
	
	public static void move(Node now) {
		for(int i = 0; i < 4; i++) {
			// System.out.print(now.toString() + " " + now.dept);
			// if(i == 0) {
			// 	System.out.println("right");
			// } else if(i == 1) {
			// 	System.out.println("left");
			// } else if(i == 2) {
			// 	System.out.println("down");
			// } else {
			// 	System.out.println("up");
			// }
			
			
			Position red = new Position(now.red);
			Position blue = new Position(now.blue);
			String[][] map = copyMap(now.map);
			
			boolean cango = true;
			boolean redDrop = false;
			boolean blueDrop = false;
			while(cango) {
				cango = false;
				if(red != null && map[red.r + dr[i]][red.c + dc[i]].equals(".")) {
					map[red.r][red.c] = ".";
					red.r = red.r + dr[i];
					red.c = red.c + dc[i];
					map[red.r][red.c] = "R";
				}
				
				if(blue != null && map[blue.r + dr[i]][blue.c + dc[i]].equals(".")) {
					map[blue.r][blue.c] = ".";
					blue.r = blue.r + dr[i];
					blue.c = blue.c + dc[i];
					map[blue.r][blue.c] = "B";
				}
				
				// 파란색이 구멍에 빠지면 false
				if(blue != null && map[blue.r + dr[i]][blue.c + dc[i]].equals("O")) {
					map[blue.r][blue.c] = ".";
					blueDrop = true;
					break;
				// 빨간색이 구멍에 들어가면 true;
				} else if(red != null && map[red.r + dr[i]][red.c + dc[i]].equals("O")) {
					map[red.r][red.c] = ".";
					redDrop = true;
					red = null;
				}
				
				// 더 이용 할 수 있는지
				if((red != null && (map[red.r + dr[i]][red.c + dc[i]].equals(".") || map[red.r + dr[i]][red.c + dc[i]].equals("O"))) || 
						(blue != null && (map[blue.r + dr[i]][blue.c + dc[i]].equals(".") || map[blue.r + dr[i]][blue.c + dc[i]].equals("O")))) {
					cango = true;
				}
			}
			
			// printMap(now.map, map);
			
			if(redDrop && !blueDrop) {
				result = now.dept + 1;
			} else if(!blueDrop && (now.dept + 1) < 10) {
				Node node = new Node(map, red, blue, now.dept + 1);
				if(!visited.contains(node.toString())) {
					visited.add(node.toString());
					que.add(node);
				}
			}
		}
	}
	
	public static String[][] copyMap(String[][] map){
		String[][] tmp = new String[map.length][map[0].length];
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}
	
	public static void printMap(String[][] old, String[][] map) {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(old[i][j]);
			}
			System.out.print("       ");
			
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static class Node {
		String[][] map;
		Position red ;
		Position blue;
		int dept;
		
		public Node() {
			this.dept = 0;
		}
		
		public Node(String[][] map, Position red, Position blue, int dept) {
			this.map = map;
			this.red = red;
			this.blue = blue;
			this.dept = dept;
		}
		
		public String toString() {
			return red.toString() + " " + blue.toString();
		}
	}
	
	public static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Position(Position p) {
			this.r = p.r;
			this.c = p.c;
		}
		
		public String toString() {
			return r + " " + c;
		}
	}
}