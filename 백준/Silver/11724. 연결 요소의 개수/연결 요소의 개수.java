import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, List<Integer>> map = new LinkedHashMap<>();
	static Queue<Integer> que = new LinkedList<>();
	static List<Integer> list = new LinkedList<>();
	static boolean[] visited;
	
	//연결된 정점이 있는지 확인 -> 있으면 다른 연결된 정점 있는지 확인
	public static void bfs() {
		int tmp;
		while(!que.isEmpty()) {
			tmp = que.poll();
			for(int i=0; i<map.get(tmp).size(); i++) {
				if(!visited[map.get(tmp).get(i)]) {
					visited[map.get(tmp).get(i)] = true;
					que.add(map.get(tmp).get(i));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		
		int a, b;
		List<Integer> tmp;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(a)) map.get(a).add(b);
			else {
				tmp = new LinkedList<>();
				tmp.add(b);
				map.put(a, tmp);
			}
			
			if(map.containsKey(b)) map.get(b).add(a);
			else {
				tmp = new LinkedList<>();
				tmp.add(a);
				map.put(b, tmp);
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!map.containsKey(i)) map.put(i, new LinkedList<>());
		}
		
		int cnt=0;
		for(int i = 1; i<=N; i++) {
			if(!visited[i]) {
				que.add(i);
				visited[i] = true;
				bfs();
				cnt++;
			}
		}
		
		
		System.out.println(cnt);
		
	}
}
