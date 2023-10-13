import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static int Q, testerCount, count;
	public static int time = 0;
	
	public static Node[] tester;
	public static PriorityQueue<Integer> restTester = new PriorityQueue<>();
	public static Map<String, Boolean> testerDomain = new LinkedHashMap<>();
	
	public static Map<String, Node> endTime = new LinkedHashMap<>();
	
	public static Map<String, PriorityQueue<Node>> waiting = new HashMap<>();
	public static Set<String> waitingUrl = new HashSet<>();
	
	public static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		String cmd = st.nextToken();
		testerCount = Integer.parseInt(st.nextToken());
		String[] url = st.nextToken().split("/");
		
		tester = new Node[testerCount + 1];	// 1번 부터 사용
		for(int i = 1; i <= testerCount; i++) {
			restTester.add(i);
		}
		
		requestScoring(new Node(0, 1, url[0], Integer.parseInt(url[1])));
		
//		printInfo();
		int priority, testerId;
		for (int i = 1; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			
			// 채점 요청
			if(cmd.equals("200")) {
				time = Integer.parseInt(st.nextToken());
				priority = Integer.parseInt(st.nextToken());
				url = st.nextToken().split("/");
				
				requestScoring(new Node(time, priority, url[0], Integer.parseInt(url[1])));
			// 채점 시도
			} else if(cmd.equals("300")) {
				time = Integer.parseInt(st.nextToken());
				tryScoring();
			// 채점 종료
			} else if(cmd.equals("400")) {
				time = Integer.parseInt(st.nextToken());
				testerId = Integer.parseInt(st.nextToken());
				endScoring(testerId);
			// 채점 대기 큐 조회
			} else if(cmd.equals("500")) {
				time = Integer.parseInt(st.nextToken());
				searchQueue();
			}
//			printInfo();
		}
//		System.out.println(sb.toString());
		System.out.println(result);
	}
	
	public static void printInfo() {
		System.out.println(time+"\n");
		System.out.println("waiting :"+waiting.toString());
		System.out.println("tester  :"+Arrays.toString(tester));
		System.out.println("retester:"+restTester.toString());
		System.out.println("endTime :"+endTime.toString());
		System.out.println("waitUrl :"+waitingUrl.toString());
		System.out.println("count   :"+count+"\n");
		System.out.println("========================================");
	}
	
	public static void requestScoring(Node node) {
		String url = node.domain+"/"+node.id;
		// 같은 url 이 존재 하면 추가 하지 않음
		if(waitingUrl.contains(url)) {
			return;
		}
		
		if(!waiting.containsKey(node.domain)) {
			waiting.put(node.domain, new PriorityQueue<>());
		}
		
		waiting.get(node.domain).add(node);
		waitingUrl.add(url);
		count++;
	}
	
	public static void tryScoring() {
		// 비어있는 채점기 찾기
		if(restTester.isEmpty()) {
			return;
		}
		
		// 채점기에 같은 도메인이 있는지 확인
		PriorityQueue<Node> tmpQue = new PriorityQueue<>();
		// 우선 순위 대로 확인
		for(String domain : waiting.keySet()) {
			if(waiting.get(domain).size() == 0) {
				continue;
			}
			
			// 이미 테스트 중인 도메인일 경우
			if(testerDomain.get(domain) != null) {
				continue;
			}
			
			// 해당 도메인의 이전에 끝난 시간 비교
			if(endTime.containsKey(domain)) {
				Node lastNode = endTime.get(domain);
				int testTime = lastNode.end - lastNode.start;
				
				// 부적절한 채점일 경우
				if(time < lastNode.start + 3 * testTime) {
					continue;
				} else {
					tmpQue.add(waiting.get(domain).peek());
				}
			} else {
				tmpQue.add(waiting.get(domain).peek());
			}
		}
		
		if(!tmpQue.isEmpty()) {
			Node node = waiting.get(tmpQue.poll().domain).poll();
			// 현재 시간을 시작 시간으로
			node.start = time;
			// 비어있는 채점기에 넣음
			tester[restTester.poll()] = node;
			testerDomain.put(node.domain, true);
			waitingUrl.remove(node.domain+"/"+node.id);
			count--;
		}
	}

	public static void endScoring(int testerId) {
		Node node = tester[testerId];
		
		// 해당 채점기가 비어 있으면 return
		if(node == null) {
			return;
		}
		
		node.end = time;
		endTime.put(node.domain, node);
		testerDomain.remove(node.domain);
		restTester.add(testerId);
		tester[testerId] = null;
	}

	public static void searchQueue() {
		result.append(count+"\n");
	}
	
	public static class Node implements Comparable<Node>{
		int start;
		int end;
		int priority;
		String domain;
		int id;
		
		public Node(int start, int priority, String domain, int id) {
			this.start = start;
			this.priority = priority;
			this.domain = domain;
			this.id = id;
		}

		@Override
		public int compareTo(Node o) {
			if(this.priority == o.priority) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.priority, o.priority);
		}
		
		public String toString() {
			return "("+start+" "+end+" "+priority+" "+domain+"/"+id+")";
		}
	}
}
