import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			long result = highway(N, M, K);

			System.out.println("Test case " + testCase + ": " + result);
		}

	}

	public static long highway(int N, int M, int K) throws IOException {
		PriorityQueue<Road> roads = new PriorityQueue<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());

			int east = Integer.parseInt(st.nextToken());
			int west = Integer.parseInt(st.nextToken());

			roads.add(new Road(east, west));
		}

		long crossCount = 0;

		long[] tree = new long[M + 1];
		while (!roads.isEmpty()) {
			Road road = roads.poll();

			tree[road.west]++;

			crossCount += getSum(tree, M, road.west);
		}

		return crossCount;
	}

	public static long getSum(long[] tree, int M, int start) {
		long sum = 0;

		for (int i = start + 1; i <= M; i++) {
			sum += tree[i];
		}

		return sum;
	}

	public static class Road implements Comparable<Road> {
		int east;
		int west;

		public Road(int east, int west) {
			this.east = east;
			this.west = west;
		}

		@Override
		public int compareTo(Road o) {
			if (this.east == o.east) {
				return Integer.compare(this.west, o.west);
			} else {
				return Integer.compare(this.east, o.east);
			}
		}
	}
}