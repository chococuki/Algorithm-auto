import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int K, W, H, board[][];
	// K값에 따른 방문 여부 저장
	static boolean visited[][][];
	static int[] dxh = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dyh = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] dxm = { 0, 1, 0, -1 };
	static int[] dym = { 1, 0, -1, 0 };
	//최적 경로를 위한 pq
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int k;
		int cnt;

		public Node(int x, int y, int k, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", k=" + k + ", cnt=" + cnt + "]";
		}
	}

	private static int move() {
		int new_x, new_y;

		int result = -1;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.x==H && now.y==W) return now.cnt;
			
			//K 남아있을 경우
			if (now.k > 0) {
				for (int i = 0; i < dxh.length; i++) {
					new_x = now.x + dxh[i];
					new_y = now.y + dyh[i];

					if (check(new_x, new_y, now.k, true)) {
						if (new_x == H && new_y == W) {
							//도착지 도착하면 리턴
							return now.cnt + 1;
						}
						pq.add(new Node(new_x, new_y, now.k - 1, now.cnt + 1));
					}
				}
			}
			for (int i = 0; i < dxm.length; i++) {
				new_x = now.x + dxm[i];
				new_y = now.y + dym[i];

				if (check(new_x, new_y, now.k, false)) {
					if (new_x == H && new_y == W) {
						//도착지 도착하면 리턴
						return now.cnt + 1;
					}
					pq.add(new Node(new_x, new_y, now.k, now.cnt + 1));
				}
			}
		}
		return result;
	}
	
	//이동 가능한 곳인지 확인하는 함수
	//nowK -> 남아있는 k
	//useK -> 말처럼 이동 하였는지
	private static boolean check(int x, int y, int nowK, boolean useK) {
		if (x >= 1 && x <= H && y >= 1 && y <= W && board[x][y] != 1) {
			if (useK && nowK > 0  && !visited[x][y][nowK-1]) {
				visited[x][y][nowK-1] = true;
				return true;
			} else if (!useK && !visited[x][y][nowK]) {
				visited[x][y][nowK] = true;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[H + 1][W + 1];
		visited = new boolean[H + 1][W + 1][K + 1];

		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작 지점
		visited[1][1][K] = true;
		pq.add(new Node(1, 1, K, 0));

		System.out.println(move());
	}
}