import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] parentCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] buildTime = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}

			List<List<Integer>> buildRule = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				buildRule.add(new ArrayList<>());
			}

			parentCount = new int[N + 1];
			int[] minTime = new int[N + 1];
			Arrays.fill(minTime, -1);

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());

				buildRule.get(first).add(second);
				parentCount[second]++;
			}

			Queue<Integer> que = new ArrayDeque<>();
			for (int build : getZeroParents()) {
				que.add(build);
				minTime[build] = buildTime[build];
			}

			while (!que.isEmpty()) {
				int now = que.poll();

				for (int child : buildRule.get(now)) {
					parentCount[child]--;
					minTime[child] = Math.max(minTime[child], minTime[now] + buildTime[child]);
				}

				que.addAll(getZeroParents());
			}

			st = new StringTokenizer(br.readLine());
			int finish = Integer.parseInt(st.nextToken());

			System.out.println(minTime[finish]);
		}
	}

	public static List<Integer> getZeroParents() {
		List<Integer> list = new ArrayList<>();

		for (int i = 1; i < parentCount.length; i++) {
			if (parentCount[i] == 0) {
				parentCount[i]--;
				list.add(i);
			}
		}

		return list;
	}
}