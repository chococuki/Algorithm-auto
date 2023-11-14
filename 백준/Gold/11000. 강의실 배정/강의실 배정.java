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

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			pq.add(new Node(start, true));
			pq.add(new Node(end, false));
		}

		int maxClass = 0;
		int nowClass = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (node.type) {
				nowClass++;
				maxClass = Math.max(maxClass, nowClass);
			} else {
				nowClass--;
			}
		}

		System.out.println(maxClass);
	}

	public static class Node implements Comparable<Node> {
		int time;
		boolean type;

		public Node(int time, boolean type) {
			this.time = time;
			this.type = type;
		}

		@Override
		public int compareTo(Node o) {
			if (this.time == o.time) {
				return Boolean.compare(this.type, o.type);
			} else {
				return Integer.compare(this.time, o.time);
			}
		}
	}
}