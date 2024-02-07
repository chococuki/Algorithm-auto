class Solution {
    public boolean solution(int[][] key, int[][] lock) {
		int N = key.length;

		boolean answer = false;
		for (int i = 0; i < 4; i++) {
			int[][] rotate = new int[N][N];
			// 기본 상태
			if (i != 0) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						// 시계방향 90도 회전
						rotate[c][N - 1 - r] = key[r][c];
					}
				}
				key = rotate;
			}

			if (check(key, lock)) {
				// open 가능하면 즉시 종료
				answer = true;
				break;
			}
		}

		return answer;
	}

	public boolean check(int[][] key, int[][] lock) {
		int N = key.length;
		int M = lock.length;

		int[][] board = new int[M][M];

		// key가 lock 밖에 위치 하는 경우 -> N + M
		for (int ksr = 0; ksr < N + M; ksr++) {
			for (int ksc = 0; ksc < N + M; ksc++) {
				for (int i = 0; i < M; i++) {
					for (int j = 0; j < M; j++) {
						board[i][j] = lock[i][j];
					}
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int r = ksr - i;
						int c = ksc - j;

						if (r >= 0 && r < M && c >= 0 && c < M) {
							board[r][c] += key[i][j];
						}
					}
				}

				if (checkOpen(board)) {
					// open 가능하면 즉시 종료
					return true;
				}
			}
		}

		return false;
	}

	// lock이 모두 1이 되는지 확인
	public boolean checkOpen(int[][] board) {
		int N = board.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 1) {
					return false;
				}
			}
		}

		return true;
	}
}
