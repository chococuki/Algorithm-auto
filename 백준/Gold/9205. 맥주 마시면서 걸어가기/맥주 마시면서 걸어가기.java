import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	private static boolean dist(Node from, Node to) {
		int dist = Math.abs(from.x-to.x) + Math.abs(from.y-to.y);
//		double dist = Math.sqrt(Math.pow(from.x-to.x, 2) + Math.pow(from.y-to.y, 2));
		return dist <= 20 * 50;
	}
	
	private static boolean move(Node from, Node end, List<Node> store) {
		if(dist(from, end)) return true;
		
		boolean[] visited = new boolean[store.size()];
		
		Queue<Node> cango = new LinkedList<>();
		for (int to = 0; to < store.size(); to++) {
			if(dist(from, store.get(to))) {
				visited[to] = true;
				cango.add(store.get(to));
			}
		}
		
		while(!cango.isEmpty()) {
			Node now = cango.poll();
			if(dist(now, end)) return true;
			else {
				for (int i = 0; i < store.size(); i++) {
					if(!visited[i] && dist(now, store.get(i))) {
						visited[i] = true;
						cango.add(store.get(i));
					}
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Node> store;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			store = new ArrayList<>();
			
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				store.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			st = new StringTokenizer(br.readLine());
			Node end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			sb.append(move(start, end, store) ? "happy" : "sad");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}