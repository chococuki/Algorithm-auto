import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			boolean[][] planes = new boolean[N + 1][N + 1];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				planes[from][to] = true;
				planes[to][from] = true;
			}

			boolean[] visited = new boolean[N + 1];

			Queue<Integer> que = new ArrayDeque<>();
			que.add(1);
			visited[1] = true;

			int count = 0;
			while (!que.isEmpty()) {
				int now = que.poll();

				for (int i = 0; i <= N; i++) {
					if (!visited[i] && planes[now][i]) {
						visited[i] = true;
						count++;
						que.add(i);
					}
				}
			}

			System.out.println(count);
		}
	}
}