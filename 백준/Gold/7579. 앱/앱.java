import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int TotalMemory = Integer.parseInt(st.nextToken());

		int[] memory = new int[N + 1];
		int[] cost = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i < N + 1; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		int sum = 0;
		for (int i = 1; i < N + 1; i++) {
			int now = Integer.parseInt(st.nextToken());
			cost[i] = now;
			sum += now;
		}

		int[][] DP = new int[N + 1][sum + 1]; // memory > 10,000,000이라서 배열 선언 불가 -> Cost로 DP를 해보자

		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < sum + 1; j++) {
				if (j - cost[i] < 0)
					DP[i][j] = DP[i - 1][j];
				else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i - 1][j - cost[i]] + memory[i]);
				}
			}
		}

//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < sum + 1; j++) {
//				System.out.print(DP[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int j = 0; j < sum + 1; j++) {
			for (int i = 0; i < N + 1; i++) {
				if(DP[i][j]>=TotalMemory) {
					System.out.println(j);
					System.exit(0);
				}

			}
		}

	}

}