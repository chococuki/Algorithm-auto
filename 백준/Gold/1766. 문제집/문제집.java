import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] val;
	static Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
	
	private static void make() {
		val = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}
	}
	
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> tmp = new PriorityQueue<>();
		Queue<Integer> result = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		make();
		
		int index, value;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			index = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			
			graph.get(index).add(value);
			val[value]++;
		}
			
		for (int i = 1; i <= N; i++) {
			if(val[i]==0) {
				tmp.add(i);
			}
		}
		
		//가중치에 따라 저장
		int now;
		while(!tmp.isEmpty()) {
			now = tmp.poll();
			
			result.add(now);
			for (Integer v : graph.get(now)) {
				val[v]--;
				if(val[v]==0) {
					tmp.add(v);
				}
			}
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		while(!result.isEmpty()) {
			sb.append(result.poll()+" ");
		}
		System.out.println(sb.toString());
	}
}