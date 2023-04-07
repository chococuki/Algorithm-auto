import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, minday = Integer.MAX_VALUE;// 전체 맵의 행과 열, 백조가 도착할 때 까지 걸린 일 수
	static int[][] board;// 전체 맵
	static boolean[][] visited;// 방문 여부 확인
	static Position baekjo;// 백조의 위치
	static PriorityQueue<Position> water = new PriorityQueue<>();// 얼음이 녹는 시간을 저장하는 우선순위 큐
	// 이동할 수 있는 방향을 저장한 배열
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	
	// Position 클래스 : 좌표와 이동한 일 수를 저장하는 클래스
	static class Position implements Comparable<Position> {
		int row; // 행
		int col; // 열
		int dept; // 이동한 일 수

		public Position(int row, int col, int dept) {
			this.row = row;
			this.col = col;
			this.dept = dept;
		}

		// 이동한 일 수를 기준으로 오름차순으로 정렬
		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.dept, o.dept);
		}
	}

	// 백조와 만나는 시간을 계산하는 메소드
	private static void meet() {
		PriorityQueue<Position> minRoute = new PriorityQueue<>();
		minRoute.add(baekjo);

		visited = new boolean[R][C];
		visited[baekjo.row][baekjo.col] = true;

		// BFS 탐색을 수행
		point: while (!minRoute.isEmpty()) {
			Position now = minRoute.poll();

			for (int i = 0; i < 4; i++) {
				int row = now.row + dr[i];
				int col = now.col + dc[i];

				// 이동할 수 있는 범위에 있는 경우
				if (row >= 0 && row < R && col >= 0 && col < C && !visited[row][col]) {
					// 백조가 도착한 경우
					if (board[row][col] == -1) {
						minday = now.dept;
						break point;
					}
					// 이동할 수 있는 경우
					else {
						// 이전에 방문하지 않았거나, 이동한 일 수가 더 작은 경우
						if (now.dept < board[row][col] || !visited[row][col]) {
							visited[row][col] = true;
							// 더 늦게 도착하는 경우를 고려하여 얼음이 녹는 시간을 계산
							minRoute.add(new Position(row, col, Math.max(now.dept, board[row][col])));
						}
					}
				}
			}
		}
	}

	// 얼음이 녹는 시간을 계산하는 메소드
	private static void iceMelt() {
		while (!water.isEmpty()) {
			Position now = water.poll();

			for (int i = 0; i < 4; i++) {
				int row = now.row + dr[i];
				int col = now.col + dc[i];

				// 이동할 수 있는 범위에 있는 경우
				if (row >= 0 && row < R && col >= 0 && col < C && !visited[row][col]) {
					visited[row][col] = true;
					// 얼음이 녹는 경우
					if (board[row][col] == Integer.MAX_VALUE) {
						board[row][col] = now.dept + 1;
						// 녹은 얼음의 위치와 녹는 시간을 저장
						water.add(new Position(row, col, now.dept + 1));
					}
				}
			}
		}
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
				if (str.charAt(c) == '.') {
					board[r][c] = 0;
					water.add(new Position(r, c, 0));
				} else if (str.charAt(c) == 'X') {
					board[r][c] = Integer.MAX_VALUE;
				} else {
					board[r][c] = -1;
					baekjo = new Position(r, c, 0);
					water.add(new Position(r, c, 0));
				}
			}
		}
		
		visited = new boolean[R][C];

		// 얼음이 녹는 시간을 미리 계산
		iceMelt();

		// 백조와 만나는 시간을 계산
		meet();

		// 백조와 만나는 시간을 출력
		System.out.println(minday);
	}
}