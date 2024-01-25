import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int M, N;
	public static int[][] map;
	public static int[][] visited;
	public static int[] dm = {0, 0, 1, -1};
	public static int[] dn = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new int[M][N];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				map[m][n] = Integer.parseInt(st.nextToken());
				visited[m][n] = -1;
			}
		}

		System.out.println(dfs(0, 0));

	}

	public static int dfs(int m, int n) {
		if (m == M - 1 && n == N - 1) {
			return 1;
		}

		if (visited[m][n] != -1) {
			return visited[m][n];
		}

		int result = 0;

		for (int i = 0; i < 4; i++) {
			int newM = m + dm[i];
			int newN = n + dn[i];

			if (newM >= 0 && newM < M && newN >= 0 && newN < N && map[m][n] > map[newM][newN]) {
				result += dfs(newM, newN);
			}
		}

		visited[m][n] = result;
		return result;
	}
}