import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[][][] dp = new int[2][T + 1][W + 1];

		int now = Integer.parseInt(br.readLine());

		if (now == 1) {
			dp[0][1][0] = 1;
			dp[1][1][1] = 0;

		} else {
			dp[0][1][0] = 0;
			dp[1][1][1] = 1;
		}

		for (int t = 2; t < T + 1; t++) {
			int nowJadu = Integer.parseInt(br.readLine());

			if (nowJadu == 1) {

				dp[0][t][0] = dp[0][t - 1][0] + 1;
				dp[1][t][0] = dp[1][t - 1][0];

				for (int w = 1; w < W + 1; w++) {
					dp[0][t][w] = Math.max(dp[0][t - 1][w], dp[1][t - 1][w - 1]) + 1;
					dp[1][t][w] = Math.max(dp[0][t - 1][w - 1], dp[1][t - 1][w]);
				}
			} else {
				dp[0][t][0] = dp[0][t - 1][0];
				dp[1][t][0] = dp[1][t - 1][0] + 1;
				for (int w = 1; w < W + 1; w++) {

					dp[0][t][w] = Math.max(dp[0][t - 1][w], dp[1][t - 1][w - 1]);
					dp[1][t][w] = Math.max(dp[0][t - 1][w - 1], dp[1][t - 1][w]) + 1;
				}
			}

		}

		int max = 0;
		for (int w = 0; w <= W; w++) {
			max = Math.max(max, Math.max(dp[0][T][w], dp[1][T][w]));
		}

		System.out.println(max);
	}

}