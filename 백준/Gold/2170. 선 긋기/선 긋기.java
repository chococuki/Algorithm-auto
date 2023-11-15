import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			pq.add(new Node(start, true));
			pq.add(new Node(end, false));
		}

		int length = 0;
		int count = 0;
		int start = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (node.type) {
				if (count == 0) {
					start = node.point;
				}
				count++;
			} else {
				count--;
				if (count == 0) {
					length += node.point - start;
				}
			}
		}

		System.out.println(length);
	}

	public static class Node implements Comparable<Node> {
		int point;
		boolean type;

		public Node(int point, boolean type) {
			this.point = point;
			this.type = type;
		}

		@Override
		public int compareTo(Node o) {
			if (this.point == o.point) {
				return Boolean.compare(this.type, o.type);
			} else {
				return Integer.compare(this.point, o.point);
			}
		}
	}
}