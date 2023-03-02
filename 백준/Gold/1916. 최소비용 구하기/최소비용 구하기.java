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
	static int N, M, min=Integer.MAX_VALUE;
	static int[] arr;
	static Map<Integer, List<Node>> bus = new LinkedHashMap<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			return Integer.compare(o1.price, o2.price);
		}
	});
	
	static class Node {
		int to, price;

		public Node(int to, int price) {
			super();
			this.to = to;
			this.price = price;
		}
		
	}
	
	private static void make() {
		arr = new int[N+1];
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		for(int i=1; i<=N; i++) {
			bus.put(i, new ArrayList<>());
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		make();
		
		int from, to, price;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from=Integer.parseInt(st.nextToken());
			to=Integer.parseInt(st.nextToken());
			price=Integer.parseInt(st.nextToken());
			
			bus.get(from).add(new Node(to, price));
		}
		
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		arr[start]=0;
		for(Node node : bus.get(start)) {
			pq.add(node);
		}
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			
			if(now.to==end) min=Math.min(min, now.price);
			if(arr[now.to] > now.price) {
				arr[now.to] = now.price;
				
				for(Node node : bus.get(now.to)) {
					pq.add(new Node(node.to, node.price+now.price));
				}
			}
		}
		
		System.out.println(min);
	}
}