import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited = new boolean[101];
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static Map<Integer, Integer> road = new LinkedHashMap<>();
	
	static class Node implements Comparable<Node>{
		int now, cnt;

		public Node(int now, int cnt) {
			super();
			this.now = now;
			this.cnt = cnt;
		}

		@Override
		//이동 횟수가 적은 순으로 정렬 -> 이동 횟수가 같으면 더 멀리 이동한것이 우선순
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) {
				return Integer.compare(o.now, this.now);
			}
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
	
	//주사위 굴리며 이동
	private static int dice() {
		whilebreak:
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(node.now==100) return node.cnt;
			for (int i = 1; i <= 6; i++) {
				int new_now = node.now + i;
				if(new_now<=100 && !visited[new_now]) {
					visited[new_now] = true;
					//도착한 곳에 사다리나 뱀이 있을 경우 이동
					if(road.containsKey(new_now)) {
						pq.add(new Node(road.get(new_now), node.cnt+1));
					//없을 경우 해당 위치로 이동
					} else {
						pq.add(new Node(new_now, node.cnt+1));
					}
				}
			}
		}
	
		return 100;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			road.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		pq.add(new Node(1, 0));	//현재 위치
		visited[1] = true;
		int result = dice();
		
		System.out.println(result);
	}
}