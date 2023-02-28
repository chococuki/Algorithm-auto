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
	static boolean[] visited;
	static Map<Integer, List<Integer>> map = new LinkedHashMap<Integer, List<Integer>>();
	static Queue<Integer> que = new LinkedList<>();
	static Queue<Integer> tmpque = new LinkedList<>();
	
	//두명의 촌수를 찾는 함수
	private static int find(int a, int b) {
		que.add(a);
		
		int cnt=0; //촌수 저장
		boolean isfind = false;
		while(!que.isEmpty()) {
			cnt++;
			isfind = bfs(b);
			if(isfind) break;	//찾았을 경우 종료
		}
		
		if(isfind) return cnt;	//찾았으면 촌수 반환
		else return -1;			//못찾았으면 -1 반환
	}
	
	private static boolean bfs(int b) {
		tmpque = new LinkedList<>(que);
		que = new LinkedList<>();
		
		int tmp;
		while(!tmpque.isEmpty()) {
			tmp = tmpque.poll();
			visited[tmp] = true;
			
			for(int j:map.get(tmp)) {
				if(!visited[j]) que.add(j);
				if(j==b) return true;	//찾으면 true 반환
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		
		//부모와 자식간의 관계를 map에 저장
		int child, parent;
		List<Integer> tmp;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			parent = Integer.parseInt(st.nextToken());
			child = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(parent)) map.get(parent).add(child);
			else {
				tmp = new LinkedList<>();
				tmp.add(child);
				map.put(parent, tmp);
			}
			if(map.containsKey(child)) map.get(child).add(parent);
			else {
				tmp = new LinkedList<>();
				tmp.add(parent);
				map.put(child, tmp);
			}
			
		}
		
		int result = find(a, b);
		
		System.out.println(result);
	}
}
