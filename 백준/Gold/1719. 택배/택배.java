import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Map<Integer, List<Node>> routes = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			routes.put(i, new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			routes.get(a).add(new Node(b, w));
			routes.get(b).add(new Node(a, w));
		}

		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			map[i][i] = -1;
			boolean[] visited = new boolean[n + 1];
			visited[i] = true;

			PriorityQueue<Route> pq = new PriorityQueue<>();
			for (Node node : routes.get(i)) {
				pq.add(new Route(node.to, node.to, node.weight));
			}

			while (!pq.isEmpty()) {
				Route route = pq.poll();

				if (!visited[route.now]) {
					visited[route.now] = true;
					map[i][route.now] = route.first;

					for (Node node : routes.get(route.now)) {
						pq.add(new Route(route.first, node.to, route.weight + node.weight));
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					sb.append("-");
				} else {
					sb.append(map[i][j]);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static class Route implements Comparable<Route> {
		int first;
		int now;
		int weight;

		public Route(int first, int now, int weight) {
			this.first = first;
			this.now = now;
			this.weight = weight;
		}

		public int compareTo(Route r) {
			return Integer.compare(this.weight, r.weight);
		}
	}

	public static class Node {
		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}