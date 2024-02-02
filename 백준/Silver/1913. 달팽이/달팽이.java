import java.util.Scanner;

public class Main {
	public static int[] dr = {1, 0, -1, 0};
	public static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int find = sc.nextInt();

		int[][] map = new int[N + 1][N + 1];

		int dir = 0;
		int r = 1;
		int c = 1;
		int[] result = new int[2];
		for (int num = (int)Math.pow(N, 2); num > 0; num--) {
			map[r][c] = num;

			if (num == find) {
				result[0] = r;
				result[1] = c;
			}

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr <= 0 || nc <= 0 || nr > N || nc > N || map[nr][nc] != 0) {
				dir = (dir + 1) % 4;
				nr = r + dr[dir];
				nc = c + dc[dir];
			}

			r = nr;
			c = nc;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		sb.append(result[0]).append(" ").append(result[1]);

		System.out.println(sb.toString());
	}
}