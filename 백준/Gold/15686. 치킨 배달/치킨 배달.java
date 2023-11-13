import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, homeCount, storeCount, result;
	public static int[][] map;
	public static List<Node> homes;
	public static List<Node> stores;
	public static boolean[] open;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		homes = new ArrayList<>();
		stores = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					homes.add(new Node(i, j, 0));
				} else if (map[i][j] == 2) {
					stores.add(new Node(i, j, 0));
				}
			}
		}

		storeCount = stores.size();
		homeCount = homes.size();
		open = new boolean[storeCount];
		result = Integer.MAX_VALUE;

		dfs(0, 0);

		System.out.println(result);
	}

	public static void dfs(int count, int start) {
		if (count == M) {
			result = Math.min(result, findDistance());
		}

		for (int i = start; i < storeCount; i++) {
			open[i] = true;
			dfs(count + 1, i + 1);
			open[i] = false;
		}
	}

	public static int findDistance() {
		int distance = 0;

		for (int home = 0; home < homeCount; home++) {
			int minDistance = Integer.MAX_VALUE;
			for (int store = 0; store < storeCount; store++) {
				if (open[store]) {
					minDistance = Math.min(minDistance, chickenDistance(home, store));
				}
			}
			distance += minDistance;
		}

		return distance;
	}

	public static int chickenDistance(int h, int s) {
		Node home = homes.get(h);
		Node store = stores.get(s);
		return Math.abs(home.c - store.c) + Math.abs(home.r - store.r);
	}

	public static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}