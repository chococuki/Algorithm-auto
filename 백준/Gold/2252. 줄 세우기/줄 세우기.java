import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static List<List<Integer>> list;
	public static boolean[] visited;
	public static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int front = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			list.get(front).add(end);
		}

		visited = new boolean[N + 1];
		stack = new Stack<>();
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				find(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}

		System.out.println(sb);
	}

	public static void find(int i) {
		visited[i] = true;

		for (int n : list.get(i)) {
			if (!visited[n]) {
				find(n);
			}
		}

		stack.push(i);
	}
}