import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.weight, this.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		
	}
	
	
	//[index][0:부모, 1:중량]
	static Edge[] edgeList;
	static int[] arr;
	
	private static boolean union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap==bp) return false;
		else {
			if(ap<bp) arr[bp]=ap;
			else arr[ap] = bp;
			return true;
		}
	}
	
	private static int find(int n) {
		if(arr[n]==n) return n;
		else return find(arr[n]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
		
		edgeList = new Edge[M];
		
		int a, b, weight;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(a, b, weight);
		}
		
		Arrays.sort(edgeList);
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		int result=0;
		boolean useA=false, useB=false;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result = edge.weight;
				if(edge.from==a || edge.to==a) useA=true;
				if(edge.from==b || edge.to==b) useB=true;
				if(useA && useB) {
					if(find(a)==find(b)) break;
				}
			}
		}
		
		System.out.println(result);
	}
}