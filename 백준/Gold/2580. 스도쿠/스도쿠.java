import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class node {
	int x;
	int y;

	public node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static String[] in;
	static int cnt = 0;
	static int[][] map = new int[9][9];
	static ArrayList<node> nodeAry = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			in = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				int now = Integer.parseInt(in[j]);
				map[i][j] = now;
				if (now == 0) {
					node nd = new node(i, j);
					cnt++;
					nodeAry.add(nd);
				}
			}
		}

		dfs(0);
	}

	public static void dfs(int depth) {

		if (depth == cnt) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
		}

		for (int i = 1; i < 10; i++) {

			int nowX = nodeAry.get(depth).x;
			int nowY = nodeAry.get(depth).y;
			
			map[nowX][nowY] = i;
			if (check(nowX, nowY))dfs(depth + 1);
			map[nowX][nowY] = 0;
		}

	}

	public static boolean check(int x, int y) {

		int value = map[x][y];

		// 열 조사
		for (int i = 0; i < 9; i++) {
			if (i != x) {
				if (map[i][y] == value) {
					return false;
				}
			}
		}

		// 행 조사
		for (int i = 0; i < 9; i++) {
			if (i != y) {
				if (map[x][i] == value) {
					return false;
				}
			}
		}

		// 사각형 검사
		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;

		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (i != x && j != y) {
					if (map[i][j] == value) {
						return false;
					}
				}
			}
		}

		return true;
	}

}