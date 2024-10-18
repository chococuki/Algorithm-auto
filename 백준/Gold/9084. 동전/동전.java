import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// testcase
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			// coin 갯수
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[] coins = new int[N];	// 코인 종류 배열
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				coins[n] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			int[] price = new int[M + 1];	// 0~M원 까지의 경우의 수
			price[0] = 1;
			
			// coin을 사용해서 만들 수 있는 경우의 수
			for (int coin : coins) {
				for (int i = coin; i <= M; i++) {
					price[i] += price[i - coin];
				}
			}

			System.out.println(price[M]);
		}
	}
}