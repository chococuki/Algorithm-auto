import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dr = {0, 0, 1, -1};
	public static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());

			if (row == 0 && col == 0) {
				break;
			}

			char[][] room = new char[row][col];

			for (int r = 0; r < row; r++) {
				room[r] = br.readLine().toCharArray();
			}

			int result = start(row, col, room);

			System.out.println(result);
		}

	}

	public static int start(int row, int col, char[][] room) {
		List<Position> dirtyList = findDirtyPosition(row, col, room);

		int[][] dirtyDist = getAllDirtyDist(row, col, room, dirtyList);

		int result;
		if (dirtyDist == null) {
			result = -1;
		} else {
			int[] robotDist = findRobotDist(row, col, room, dirtyList);

			result = findMinDist(robotDist, dirtyDist);
		}

		return result;
	}

	// 더러운 위치 모두 찾기
	public static List<Position> findDirtyPosition(int row, int col, char[][] room) {
		List<Position> dirtyList = new ArrayList<>();

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (room[r][c] == '*') {
					dirtyList.add(new Position(r, c, 0));
				}
			}
		}

		return dirtyList;
	}

	// 더러운 위치간의 최단 거리 찾기
	public static int[][] getAllDirtyDist(int row, int col, char[][] room, List<Position> dirtyList) {
		int dirtyCount = dirtyList.size();

		int[][] dirtyDist = new int[dirtyCount][dirtyCount];

		List<Position> distList;
		for (int i = 0; i < dirtyCount; i++) {
			distList = new ArrayList<>();

			getDistList(row, col, distList, dirtyList.get(i), room);

			if (distList.size() != dirtyCount - 1 || distList.isEmpty()) {
				// 더러운 곳을 모두 청소할 수 없을 경우 null
				return null;
			} else {
				getDist(dirtyDist[i], dirtyList, distList, dirtyCount);
			}
		}

		return dirtyDist;
	}

	// start 로 부터 distList 간의 최단 거리
	public static void getDistList(int row, int col, List<Position> distList, Position start, char[][] room) {
		Queue<Position> que = new ArrayDeque<>();
		que.add(start);

		boolean[][] visited = new boolean[row][col];
		visited[start.row][start.col] = true;

		while (!que.isEmpty()) {
			Position pos = que.poll();

			for (int i = 0; i < 4; i++) {
				int nr = pos.row + dr[i];
				int nc = pos.col + dc[i];

				if (nr >= 0 && nr < row && nc >= 0 && nc < col && !visited[nr][nc] && room[nr][nc] != 'x') {
					visited[nr][nc] = true;

					if (room[nr][nc] == '*') {
						distList.add(new Position(nr, nc, pos.count + 1));
					}

					que.add(new Position(nr, nc, pos.count + 1));
				}
			}
		}
	}

	public static void getDist(int[] dist, List<Position> dirtyList, List<Position> distList, int dirtyCount) {
		for (int i = 0; i < dirtyCount; i++) {
			Position pos1 = dirtyList.get(i);

			for (Position pos2 : distList) {
				if (pos1.row == pos2.row && pos1.col == pos2.col) {
					dist[i] = pos2.count;
					break;
				}
			}
		}
	}

	// 로봇에서 더러운곳 까지의 최단 거리
	public static int[] findRobotDist(int row, int col, char[][] room, List<Position> dirtyList) {
		int[] dist = new int[dirtyList.size()];

		Position robot = findRobot(row, col, room);

		List<Position> distList = new ArrayList<>();

		getDistList(row, col, distList, robot, room);

		getDist(dist, dirtyList, distList, dirtyList.size());

		return dist;
	}

	// 로봇 위치
	public static Position findRobot(int row, int col, char[][] room) {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (room[r][c] == 'o') {
					return new Position(r, c, 0);
				}
			}
		}

		return null;
	}

	// 더러운 곳을 모두 청소하는 최단 거리 찾기
	public static int findMinDist(int[] robotDist, int[][] dirtyDist) {
		int dirtyCount = robotDist.length;

		boolean[] visited = new boolean[dirtyCount];
		int minDist = Integer.MAX_VALUE;
		// 로봇이 처음으로 청소할곳
		for (int i = 0; i < dirtyCount; i++) {
			visited[i] = true;
			minDist = findMinDistDFS(visited, dirtyDist, i, 1, robotDist[i], minDist);
			visited[i] = false;
		}

		return minDist;
	}

	// 모든 경우를 확인 하며 최단거리 찾기
	public static int findMinDistDFS(boolean[] visited, int[][] dirtyDist, int from, int count, int dist,
		int minDist) {
		if (count == visited.length) {
			minDist = Math.min(dist, minDist);
			return minDist;
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dist += dirtyDist[from][i];
				minDist = findMinDistDFS(visited, dirtyDist, i, count + 1, dist, minDist);
				dist -= dirtyDist[from][i];
				visited[i] = false;
			}
		}

		return minDist;
	}

	public static class Position {
		int row;
		int col;
		int count;

		public Position(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
}