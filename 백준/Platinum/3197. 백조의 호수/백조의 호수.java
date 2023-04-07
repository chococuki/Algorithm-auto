import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, minday = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[][] visited;
    static Position baekjo;
    static PriorityQueue<Position> water = new PriorityQueue<>();
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static class Position implements Comparable<Position> {
        int row;
        int col;
        int dept;

        public Position(int row, int col, int dept) {
            this.row = row;
            this.col = col;
            this.dept = dept;
        }

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.dept, o.dept);
		}

		@Override
		public String toString() {
			return "Position [row=" + row + ", col=" + col + ", dept=" + dept + "]";
		}
		
    }

    private static void meet() {
    	PriorityQueue<Position> minRoute = new PriorityQueue<>();
    	minRoute.add(baekjo);
    	
    	visited = new boolean[R][C];
    	visited[baekjo.row][baekjo.col] = true;
    	
    	point:
    	while(!minRoute.isEmpty()) {
    		Position now = minRoute.poll();
    		
    		for (int i = 0; i < 4; i++) {
                int row = now.row + dr[i];
                int col = now.col + dc[i];
                if (row >= 0 && row < R && col >= 0 && col < C 
                		&& !visited[row][col]) {
                    if (board[row][col] == -1) {
                        minday = now.dept;
                        break point;
                    } else {
                    	if(now.dept < board[row][col] || !visited[row][col]) {
                    		visited[row][col] = true;
                    		minRoute.add(new Position(row, col, Math.max(now.dept, board[row][col])));
                    	}
                    }
                }
            }
    		
    	}
    	
        
    }

    private static void iceMelt() {
        while (!water.isEmpty()) {
            Position now = water.poll();
            
            for (int i = 0; i < 4; i++) {
                int row = now.row + dr[i];
                int col = now.col + dc[i];

                if (row >= 0 && row < R && col >= 0 && col < C && !visited[row][col]) {
                    visited[row][col] = true;
                    if (board[row][col] == Integer.MAX_VALUE) {
                    	board[row][col] = now.dept+1;
                        water.add(new Position(row, col, now.dept+1));
                    }
                }
            }
        }        
    }

    private static void start() {
        visited = new boolean[R][C];

        iceMelt();
        
        meet();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                if(str.charAt(c)=='.') {
                	board[r][c] = 0;
                	water.add(new Position(r, c, 0));
                } else if(str.charAt(c)=='X') {
                	board[r][c] = Integer.MAX_VALUE;
                } else {
                	board[r][c] = -1;
                	baekjo = new Position(r, c, 0);
                	water.add(new Position(r, c, 0));
                }
            }
        }
        start();
        
        System.out.println(minday);
    }
}