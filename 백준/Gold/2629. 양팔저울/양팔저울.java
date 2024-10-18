import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int MAX_WEIGHT = 40000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		int[] weights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] dp = new boolean[MAX_WEIGHT + 1];

		dp[0] = true;
		for (int weight : weights) {
			boolean[] tmpDp = dp.clone();
			for (int i = MAX_WEIGHT; i >= 0; i--) {
				if (dp[i]) {
					if (i + weight <= MAX_WEIGHT) {
						tmpDp[i + weight] = true;
					}
					tmpDp[Math.abs(i - weight)] = true;
				}
			}
			dp = tmpDp;
		}

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int w = Integer.parseInt(st.nextToken());

			sb.append(dp[w] ? "Y " : "N ");
		}

		System.out.println(sb.toString());
	}
}