import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int to;
		int price;

		public Node(int to, int price) {
			super();
			this.to = to;
			this.price = price;
		}

	}

	public static void main(String[] args) throws IOException {
		Map<Integer, List<Node>> bus = new LinkedHashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			bus.put(i, new ArrayList<>());
			bus.get(i).add(new Node(i, 0));
		}

		// 입력되는 노선 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			bus.get(Integer.parseInt(st.nextToken()))
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		int[][] result = new int[N + 1][N + 1];

		// 경로가 있으면 가중치저장
		for (int from = 1; from <= N; from++) {
			Arrays.fill(result[from], Integer.MAX_VALUE);

			for (Node n : bus.get(from)) {
				result[from][n.to] = Math.min(n.price, result[from][n.to]);
			}
		}

		// 경유지 거처 가는것이 더 빠르면 업데이트
		for (int stop = 1; stop <= N; stop++) {
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {
					if (result[from][stop] != Integer.MAX_VALUE && result[stop][to] != Integer.MAX_VALUE
							&& result[from][to] > result[from][stop] + result[stop][to]) {
						result[from][to] = result[from][stop] + result[stop][to];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append((result[i][j] == Integer.MAX_VALUE ? 0 : result[i][j]) + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}