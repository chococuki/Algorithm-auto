import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] food = new int[N][N];
		int[][] map = new int[N][N];
		PriorityQueue<Integer>[][] trees = new PriorityQueue[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());

				food[i][j] = tmp;
				map[i][j] = 5;
				trees[i][j] = new PriorityQueue<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			trees[r - 1][c - 1].add(a);
		}

		for (int k = 0; k < K; k++) {
			List<Node> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					PriorityQueue<Integer> tmp = new PriorityQueue<>();

					int newFood = 0;
					while (!trees[i][j].isEmpty()) {
						int age = trees[i][j].poll();
						if (map[i][j] >= age) {
							map[i][j] -= age;
							tmp.add(age + 1);

							if ((age + 1) % 5 == 0) {
								list.add(new Node(i, j));
							}
						} else {
							newFood += (age / 2);
						}
					}

					trees[i][j].addAll(tmp);
					map[i][j] += (food[i][j] + newFood);
				}
			}

			for (Node node : list) {
				for (int i = 0; i < 8; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}

					trees[nr][nc].add(1);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count += trees[i][j].size();
			}
		}

		System.out.println(count);
	}

	public static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}