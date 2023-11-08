import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		boolean[][] wall = new boolean[N + 1][M + 1];

		PriorityQueue<Node> items = new PriorityQueue<>();
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			items.add(new Node(n, m));
		}
		items.add(new Node(N, M));

		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			wall[n][m] = true;
		}

		int[] dn = {1, 0};
		int[] dm = {0, 1};
		int result = 1;
		Node last = new Node(1, 1);
		while (!items.isEmpty()) {
			Node item = items.poll();
			Queue<Node> que = new ArrayDeque<>();
			que.add(last);
			int count = 0;
			while (!que.isEmpty()) {
				Node now = que.poll();

				for (int i = 0; i < 2; i++) {
					int newN = now.n + dn[i];
					int newM = now.m + dm[i];

					// 맵 밖일때
					if (newN > N || newM > M) {
						continue;
					}

					// 벽일때
					if (wall[newN][newM]) {
						continue;
					}

					// 아이템을 못먹는 경우
					if (newN > item.n || newM > item.m) {
						continue;
					}

					if (newN == item.n && newM == item.m) {
						count++;
					} else {
						que.add(new Node(newN, newM));
					}
				}
			}
			result *= count;
			last = item;
		}

		System.out.println(result);

	}

	public static class Node implements Comparable<Node> {
		int n;
		int m;

		public Node(int n, int m) {
			this.n = n;
			this.m = m;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.n + this.m, node.n + node.m);
		}

		@Override
		public String toString() {
			return "Node[" + n + ", " + m + "]";
		}
	}
}