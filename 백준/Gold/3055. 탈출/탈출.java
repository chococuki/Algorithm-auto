import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 방향 상수 (하, 상, 우, 좌)
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int R, C; // 맵의 세로, 가로 길이
	static char[][] map; // 맵 정보 저장 배열
	static boolean[][] hvisited, wvisited; // 고슴도치, 물 방문 여부 저장 배열
	static Queue<Position> hedge = new LinkedList<>(); // 고슴도치 위치 큐
	static Queue<Position> water = new LinkedList<>(); // 물 위치 큐

	static class Position {
		int row; // 행 좌표
		int col; // 열 좌표
		int dept; // 이동 거리

		public Position(int row, int col, int dept) {
			super();
			this.row = row;
			this.col = col;
			this.dept = dept;
		}

	}

	// 고슴도치 이동 메소드
	private static int move() {
		while (!hedge.isEmpty()) {
			Queue<Position> tmpq = new LinkedList<>(hedge); // 다음 큐 생성
			hedge = new LinkedList<>(); // 고슴도치 위치 큐 초기화

			spreadWater(); // 물 번지는 위치 처리
			while (!tmpq.isEmpty()) {
				Position now = tmpq.poll(); // 큐에서 현재 위치 추출

				for (int i = 0; i < 4; i++) { // 네 방향으로 이동
					int row = now.row + dr[i];
					int col = now.col + dc[i];

					// 이동 가능한 위치인 경우
					if (row >= 0 && row < R && col >= 0 && col < C && !hvisited[row][col]) {
						hvisited[row][col] = true;
						// 벽 또는 물인 경우 이동 불가
						if (map[row][col] == 'X' || map[row][col] == '*')
							continue;
						// 비버 굴에 도착한 경우 이동 거리 반환
						else if (map[row][col] == 'D')
							return now.dept + 1;
						// 이동 가능한 위치인 경우 큐에 추가
						else {
							hedge.add(new Position(row, col, now.dept + 1));

						}
					}
				}
			}
		}

		return -1;
	}

	// 물 번지는 위치 처리 메소드
	private static void spreadWater() {
		Queue<Position> tmpq = new LinkedList<>(water); // 다음 큐 생성
		water = new LinkedList<>(); // 물 위치 큐 초기화

		while (!tmpq.isEmpty()) {
			Position now = tmpq.poll(); // 큐에서 현재 위치 추출

			for (int i = 0; i < 4; i++) { // 네 방향으로 번짐
				int row = now.row + dr[i];
				int col = now.col + dc[i];

				// 번질 수 있는 위치인 경우
				if (row >= 0 && row < R && col >= 0 && col < C && map[row][col] == '.' && !wvisited[row][col]) {
					wvisited[row][col] = true;
					water.add(new Position(row, col, now.dept + 1));
					map[row][col] = '*'; // 해당 위치를 물로 변경
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 맵의 세로 길이
		C = Integer.parseInt(st.nextToken()); // 맵의 가로 길이

		map = new char[R][C]; // 맵 정보 저장 배열 생성
		hvisited = new boolean[R][C]; // 고슴도치 방문 여부 저장 배열 생성
		wvisited = new boolean[R][C]; // 물 방문 여부 저장 배열 생성

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == 'S') { // 고슴도치 위치일 경우
					map[r][c] = '.'; // 빈 공간으로 변경
					hedge.add(new Position(r, c, 0)); // 고슴도치 위치 큐에 추가
					hvisited[r][c] = true; // 고슴도치 위치 방문 표시
				} else if (map[r][c] == '*') { // 물 위치일 경우
					water.add(new Position(r, c, 0)); // 물 위치 큐에 추가
					wvisited[r][c] = true; // 물 위치 방문 표시
				}
			}
		}

		int result = move(); // 고슴도치 이동 메소드 호출하여 결과 저장

		System.out.println(result != -1 ? result : "KAKTUS"); // 결과 출력
	}
}