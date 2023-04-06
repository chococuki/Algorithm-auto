import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 정답 상수 문자열
	static final String RESULT_STRING = "123456780";

	// 방문한 배열을 저장하기 위한 HashSet
	static HashSet<String> visited = new HashSet<>();

	// 빈 공간을 움직이는 최적 경로를 저장하기 위한 PriorityQueue
	static PriorityQueue<MoveInfo> movePriorityQueue = new PriorityQueue<>();

	static class MoveInfo implements Comparable<MoveInfo> {
		// 2차원 배열
		int[][] puzzle;
		// 움직인 횟수
		int moveCount;

		public MoveInfo(int[][] puzzle, int moveCount) {
			super();
			this.puzzle = puzzle;
			this.moveCount = moveCount;
		}

		@Override
		public int compareTo(MoveInfo o) {
			return Integer.compare(this.moveCount, o.moveCount);
		}
	}

	// 시작 위치에서 최적 경로를 찾기 위한 메소드
	private static int findOptimalRoute() {
		// 상하좌우 이동을 위한 dr, dc 배열
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		while (!movePriorityQueue.isEmpty()) {
			MoveInfo now = movePriorityQueue.poll();
			int indexOfZero = findIndexOfZero(now.puzzle);

			for (int i = 0; i < 4; i++) {
				int row = indexOfZero / 3 + dr[i];
				int col = indexOfZero % 3 + dc[i];

				// 퍼즐의 경계를 넘어가지 않게 하기 위한 조건문
				if (row >= 0 && row < 3 && col >= 0 && col < 3) {
					int tmp = now.puzzle[indexOfZero / 3][indexOfZero % 3];
					now.puzzle[indexOfZero / 3][indexOfZero % 3] = now.puzzle[row][col];
					now.puzzle[row][col] = tmp;
					String strArray = arrayToString(now.puzzle);
					if (strArray.equals(RESULT_STRING)) {
						return now.moveCount + 1;
					} else if (!visited.contains(strArray)) {
						visited.add(strArray);
						movePriorityQueue.add(new MoveInfo(copyArray(now.puzzle), now.moveCount + 1));
					}
					now.puzzle[row][col] = now.puzzle[indexOfZero / 3][indexOfZero % 3];
					now.puzzle[indexOfZero / 3][indexOfZero % 3] = tmp;
				}
			}
		}

		return -1;
	}

	// 2차원 배열에서 0의 인덱스를 찾는 메소드
	private static int findIndexOfZero(int[][] puzzle) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle[i][j] == 0)
					return i * 3 + j;
			}
		}
		return -1;
	}

	// 2차원 배열을 복사하여 반환하는 메소드
	private static int[][] copyArray(int[][] puzzle) {
		int[][] copiedArray = new int[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copiedArray[i][j] = puzzle[i][j];
			}
		}

		return copiedArray;
	}

	// 2차원 배열을 문자열로 변환하여 반환하는 메소드
	private static String arrayToString(int[][] puzzle) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(puzzle[i][j]);
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] puzzle = new int[3][3];

		// 퍼즐 초기 상태 입력
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				puzzle[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 상태를 우선순위 큐에 추가
		movePriorityQueue.add(new MoveInfo(puzzle, 0));

		// 초기 상태가 정답이 아닌 경우 최적 경로를 찾아 출력
		System.out.println(!arrayToString(puzzle).equals(RESULT_STRING) ? findOptimalRoute() : 0);
	}
}