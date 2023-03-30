import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			int count = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[] coin = new int[count]; // 가진 동전 리스트

			for (int n = 0; n < count; n++) {
				coin[n] = Integer.parseInt(st.nextToken());
			}

			int capa = Integer.parseInt(br.readLine()); // 만들어야 하는 금액

			int[] dp = new int[capa + 1];
			dp[0] = 1;
			
			for (int i = 0; i < count; i++) {
				for (int j = coin[i]; j < capa + 1; j++) {
					if (j - coin[i] >= 0) {
						dp[j] += dp[j-coin[i]];
					}
				}
			}

			System.out.println(dp[capa]);
		}

	}

}