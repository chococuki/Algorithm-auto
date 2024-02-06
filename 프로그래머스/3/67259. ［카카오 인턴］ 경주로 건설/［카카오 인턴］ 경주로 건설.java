import java.util.*;

class Solution {
    public static int STRAIGHT_PRICE = 100;
	public static int CORNER_PRICE = 500;
	public static int[] hdr = {0, 0};
	public static int[] hdc = {1, -1};
	public static int[] vdc = {0, 0};
	public static int[] vdr = {1, -1};

	public int solution(int[][] board) {
		int N = board.length;

		int[][] prices = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(prices[i], Integer.MAX_VALUE);
		}

		Queue<Position> que = new ArrayDeque<>();
		que.add(new Position(0, 0, 0, true));
		que.add(new Position(0, 0, 0, false));

		while (!que.isEmpty()) {
			Position now = que.poll();

			// 이전과 같은 방향으로 이동 -> 직선
			for (int i = 0; i < 2; i++) {
				int nr = now.row + (now.hor ? hdr[i] : vdr[i]);
				int nc = now.col + (now.hor ? hdc[i] : vdc[i]);
				int price = now.price + STRAIGHT_PRICE;

				// 비용이 같아도 이동 방향이 다를 수 있음
				// 이동후 직진 하는 경우 비용 < 이동 후 직각 이동 하는 경우
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && prices[nr][nc] >= price - 500 && board[nr][nc] == 0) {
					prices[nr][nc] = Math.min(prices[nr][nc], price);
					que.add(new Position(nr, nc, price, now.hor));
				}
			}

			// 이전과 다른 방향으로 이동 -> 직각
			for (int i = 0; i < 2; i++) {
				int nr = now.row + (now.hor ? vdr[i] : hdr[i]);
				int nc = now.col + (now.hor ? vdc[i] : hdc[i]);
				int price = now.price + CORNER_PRICE + STRAIGHT_PRICE;    // 직각 후 직선 도로 있음

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && prices[nr][nc] >= price - 500 && board[nr][nc] == 0) {
					prices[nr][nc] = Math.min(prices[nr][nc], price);
					que.add(new Position(nr, nc, price, !now.hor));
				}
			}

		}

		return prices[N - 1][N - 1];
	}

	public static class Position {
		int row;
		int col;
		int price;
		boolean hor;

		public Position(int row, int col, int price, boolean hor) {
			this.row = row;
			this.col = col;
			this.price = price;
			this.hor = hor;
		}
	}
}