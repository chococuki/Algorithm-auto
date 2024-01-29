import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] map = new long[2 * N + 1][2 * M + 1];

		int K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[a + c][b + d] = -1;
		}

		map[0][0] = 1;

		for (int i = 2; i <= 2 * N; i += 2) {
			if (map[i - 1][0] != -1)
				map[i][0] = map[i - 2][0];
		}

		for (int i = 2; i <= 2 * M; i += 2) {
			if (map[0][i - 1] != -1)
				map[0][i] = map[0][i - 2];
		}

		for (int i = 2; i <= 2 * N; i += 2) {
			for (int j = 2; j <= 2 * M; j += 2) {
				if (map[i - 1][j] != -1) {
					map[i][j] += map[i - 2][j];
				}
				if (map[i][j - 1] != -1) {
					map[i][j] += map[i][j - 2];
				}
			}
		}

		System.out.println(map[2 * N][2 * M]);

	}
}