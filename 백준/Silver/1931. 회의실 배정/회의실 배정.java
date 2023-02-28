import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Node> list = new ArrayList<>();
	public static class Node {
		int start, end;

		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Comparator<Node> com = new Comparator<Main.Node>() {
			
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.end == o2.end) {
					return o1.start-o2.start;
				}
				return o1.end-o2.end;
			}
		};
		
		Collections.sort(list, com);;;
		
		int end = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(end <= list.get(i).start) {
				end = list.get(i).end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}