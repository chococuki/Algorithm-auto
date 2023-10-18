import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dr = {0, 0, 1, -1};
	public static int[] dc = {1, -1, 0, 0};
	public static int N, index;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		int[][] tmpMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tmpMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 index 설정
		setIslandIndex(tmpMap);

		// 다른섬으로 이동하는 최단 거리 찾기
		int result = findMinBridge();

		System.out.println(result);
	}

	public static int findMinBridge() {
		int result = N;
		PriorityQueue<Bridge> bridges = new PriorityQueue<>();
		boolean[][][] visited = new boolean[index][N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visited[map[i][j]][i][j]) {
					visited[map[i][j]][i][j] = true;
					int island = map[i][j];
					Queue<Node> que = new ArrayDeque<>();

					que.add(new Node(i, j));

					while (!que.isEmpty()) {
						Node node = que.poll();

						for (int d = 0; d < 4; d++) {
							int nr = node.r + dr[d];
							int nc = node.c + dc[d];

							if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
								continue;
							}

							if (visited[island][nr][nc]) {
								continue;
							}

							if (map[nr][nc] == island) {
								visited[island][nr][nc] = true;
								que.add(new Node(nr, nc));
							} else if (map[nr][nc] == 0) {
								visited[island][nr][nc] = true;
								bridges.add(new Bridge(nr, nc, island, 1));
							}
						}
					}
				}
			}
		}

		while (!bridges.isEmpty()) {
			Bridge bridge = bridges.poll();

			for (int d = 0; d < 4; d++) {
				int nr = bridge.r + dr[d];
				int nc = bridge.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}

				if (visited[bridge.i][nr][nc]) {
					continue;
				}

				if (map[nr][nc] == 0) {
					visited[bridge.i][nr][nc] = true;
					bridges.add(new Bridge(nr, nc, bridge.i, bridge.d + 1));
				} else {
					return bridge.d;
				}
			}
		}

		return N;
	}

	public static void setIslandIndex(int[][] tmpMap) {
		boolean[][] visited = new boolean[N][N];
		index = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && tmpMap[i][j] == 1) {
					visited[i][j] = true;
					Queue<Node> que = new ArrayDeque<>();
					map[i][j] = index;
					que.add(new Node(i, j));

					while (!que.isEmpty()) {
						Node node = que.poll();

						for (int d = 0; d < 4; d++) {
							int nr = node.r + dr[d];
							int nc = node.c + dc[d];

							if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
								continue;
							}

							if (visited[nr][nc]) {
								continue;
							}

							if (tmpMap[nr][nc] == 1) {
								map[nr][nc] = index;
								visited[nr][nc] = true;
								que.add(new Node(nr, nc));
							}
						}
					}
					index++;
				}
			}
		}
	}

	public static class Bridge extends Node implements Comparable<Bridge> {
		int i;
		int d;

		public Bridge(int r, int c, int i, int d) {
			super(r, c);
			this.i = i;
			this.d = d;
		}

		public int compareTo(Bridge b) {
			return Integer.compare(this.d, b.d);
		}
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}