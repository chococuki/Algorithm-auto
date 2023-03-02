import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, INF=Integer.MAX_VALUE;
	static int[] arr;
	static Map<Integer, List<Node>> line = new LinkedHashMap<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	//도착지와 가중치
	static class Node implements Comparable<Node>{
		int to, val;

		public Node(int to, int val) {
			super();
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.val, o.val);
		}
		
	}
	
	//초기화 함수
	private static void make() {
		arr = new int[V+1];
		Arrays.fill(arr, INF);
		
		for (int i = 1; i <= V; i++) {
			line.put(i, new ArrayList<>());
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		make();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			line.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		//출발지 0
		arr[K] = 0;
		pq.add(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (Node node : line.get(now.to)) {
				//저장된 가중치 보다 작은 값으로 이동 가능할때
				if(arr[node.to]>now.val+node.val) {
					arr[node.to] = now.val+node.val;
					pq.add(new Node(node.to, now.val+node.val));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sb.append(arr[i]==INF ? "INF" : arr[i]);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}