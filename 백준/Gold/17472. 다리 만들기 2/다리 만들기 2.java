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
	public static int N, M, index;
	public static int[][] map;

	public static int[][] bridge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		setIslandIndex();

		findMinBridge();

		System.out.println(findMinAllBridgeConnect());
	}

	public static int findMinAllBridgeConnect() {
		int[] parents = new int[index];
		PriorityQueue<Edge> edges = new PriorityQueue<>();

		for (int i = 1; i < index; i++) {
			for (int j = i + 1; j < index; j++) {
				if (bridge[i][j] != 0) {
					edges.add(new Edge(i, j, bridge[i][j]));
				}
			}
		}

		int minLength = 0;
		int edgeCount = 0;

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (edgeCount == index - 2) {
				break;
			}

			int fromParent = findParent(parents, edge.from);
			int toParent = findParent(parents, edge.to);

			if (fromParent != toParent) {
				minLength += edge.length;
				union(parents, fromParent, toParent);
				edgeCount++;
			}
		}

		return edgeCount == index - 2 ? minLength : -1;
	}

	public static void union(int[] parents, int from, int to) {
		int a = findParent(parents, from);
		int b = findParent(parents, to);
		parents[from] = to;
	}

	public static int findParent(int[] parents, int n) {
		if (parents[n] == 0) {
			return n;
		} else {
			return findParent(parents, parents[n]);
		}
	}

	public static void findMinBridge() {
		boolean[][] visited = new boolean[N][M];
		bridge = new int[index][index];

		int index;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] != 0 && !visited[n][m]) {
					visited = new boolean[N][M];
					visited[n][m] = true;
					index = map[n][m];
					Queue<Node> que = new ArrayDeque<>();
					que.add(new Node(n, m));

					while (!que.isEmpty()) {
						Node node = que.poll();

						for (int i = 0; i < 4; i++) {
							int nr = node.r + dr[i];
							int nc = node.c + dc[i];

							if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
								continue;
							}

							if (visited[nr][nc]) {
								continue;
							} else if (map[nr][nc] != 0) {
								visited[nr][nc] = true;
							}

							if (map[nr][nc] == index) {
								que.add(new Node(nr, nc));
							} else if (map[nr][nc] == 0) {
								int length = 1;
								while (true) {
									nr = node.r + (dr[i] * length);
									nc = node.c + (dc[i] * length);

									if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
										break;
									}

									if (map[nr][nc] != 0) {
										if (length <= 2) {
											break;
										} else if (bridge[index][map[nr][nc]] == 0) {
											bridge[index][map[nr][nc]] = length - 1;
										} else {
											bridge[index][map[nr][nc]] = Math.min(bridge[index][map[nr][nc]],
												length - 1);
										}
										break;
									}

									length++;
								}
							}
						}
					}

					index++;
				}
			}
		}
	}

	public static void setIslandIndex() {
		boolean[][] visited = new boolean[N][M];
		int[][] tmpMap = new int[N][M];

		index = 1;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 1 && !visited[n][m]) {
					visited[n][m] = true;
					Queue<Node> que = new ArrayDeque<>();
					que.add(new Node(n, m));

					while (!que.isEmpty()) {
						Node node = que.poll();
						tmpMap[node.r][node.c] = index;

						for (int i = 0; i < 4; i++) {
							int nr = node.r + dr[i];
							int nc = node.c + dc[i];

							if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && !visited[nr][nc]) {
								visited[nr][nc] = true;
								que.add(new Node(nr, nc));
							}
						}
					}

					index++;
				}
			}
		}

		map = tmpMap;
	}

	public static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int length;

		public Edge(int from, int to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.length, o.length);
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