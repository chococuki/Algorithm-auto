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

		PriorityQueue<Node> que = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			que.add(new Node(true, start));
			que.add(new Node(false, end));
		}

		int maxCount = 0;
		int count = 0;
		while (!que.isEmpty()) {
			Node node = que.poll();
			if (node.type) {
				count++;
			} else {
				count--;
			}
			maxCount = Math.max(maxCount, count);
		}

		System.out.println(maxCount);
	}

	public static class Node implements Comparable<Node> {
		boolean type;    // ture -> 시작, false -> 끝
		long time;

		public Node(boolean type, long time) {
			this.type = type;
			this.time = time;
		}

		// 시간 순으로 정렬
		// 시간이 같으면 끝내는 것이 우선순
		public int compareTo(Node n) {
			if (this.time == n.time) {
				return Boolean.compare(this.type, n.type);
			} else {
				return Long.compare(this.time, n.time);
			}
		}
	}
}
