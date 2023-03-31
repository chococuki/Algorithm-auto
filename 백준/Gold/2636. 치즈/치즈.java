import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int row, col;
	static int[][] pan;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static Set<Position> air = new LinkedHashSet<>();

	// 위치 저장
	static class Position {
		int row;
		int col;

		public Position(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

	}

	//치즈 녹이기 시작
	private static int startMelting() {

		int time = 0;
		int panSize = row * col;
		//모든 칸이 공기가 될때까지 반복
		while (air.size() < panSize) {
			time++;
			findAir();
			boolean[][] visited = new boolean[row][col];

			for (Position now : air) {
				for (int i = 0; i < 4; i++) {
					int new_r = now.row + dr[i];
					int new_c = now.col + dc[i];

					if (new_r >= 0 && new_r < row && new_c >= 0 && new_c < col && !visited[new_r][new_c]) {
						visited[new_r][new_c] = true;
						if (pan[new_r][new_c] == -1) {
							// 녹은 시간 저장
							pan[new_r][new_c] = time;
						}
					}
				}
			}
		}

		return time - 1;
	}

	// 공기 위치 찾는 함수
	private static void findAir() {
		Queue<Position> tmpQ = new LinkedList<>();
		boolean visited[][] = new boolean[row][col];

		air = new LinkedHashSet<>();
		air.add(new Position(0, 0));
		tmpQ.add(new Position(0, 0));
		visited[0][0] = true;

		while (!tmpQ.isEmpty()) {
			Position now = tmpQ.poll();

			for (int i = 0; i < 4; i++) {
				int new_r = now.row + dr[i];
				int new_c = now.col + dc[i];

				// time값이 저장되면 공기로 처리
				if (new_r >= 0 && new_r < row && new_c >= 0 && new_c < col && !visited[new_r][new_c]
						&& pan[new_r][new_c] >= 0) {
					visited[new_r][new_c] = true;
					tmpQ.add(new Position(new_r, new_c));
					air.add(new Position(new_r, new_c));
				}
			}
		}
	}

	// time에 남은 치즈 수
	private static int meltingAtTime(int time) {
		int count = 0;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (pan[r][c] == time)
					count++;
			}
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		pan = new int[row][col];

		for (int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < col; c++) {
				pan[r][c] = Integer.parseInt(st.nextToken());
				// 녹은 시간 저장을 위해 치즈 위치를 -1로 저장
				if (pan[r][c] == 1)
					pan[r][c] = -1;
			}
		}

		int time = startMelting();

		System.out.println(time);
		System.out.println(meltingAtTime(time));
	}
}