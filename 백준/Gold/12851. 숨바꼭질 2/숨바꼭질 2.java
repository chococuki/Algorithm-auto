import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N, K;
	static int[][] arr;
	static PriorityQueue<Node> que = new PriorityQueue<>();
	
	//현위치, 이동 횟수 
	static class Node implements Comparable<Node>{
		int now, cnt;

		public Node(int now, int cnt) {
			super();
			this.now = now;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}
		
	}
	
	//이동
	private static void start() {
		Node node = null;
		int tmp;
		
		while(!que.isEmpty()) {
			node = que.poll();
			
			//최소값보다 cnt가 커지면 종료
			if(arr[K][0]<=node.cnt) break;
			
			//+1로 이동
			tmp = node.now+1;
			if(tmp>=0 && tmp<=200000 && arr[tmp][0]>=node.cnt+1) {
				if(arr[tmp][0]==node.cnt+1) {	//최소값이 같으면 cnt +1
					arr[tmp][1]++;
				}
				arr[tmp][0] = node.cnt+1;
				que.add(new Node(tmp, node.cnt+1));
			}
			
			//-1로 이동
			tmp = node.now-1;
			if(tmp>=0 && tmp<=200000 && arr[tmp][0]>=node.cnt+1) {
				if(arr[tmp][0]==node.cnt+1) {
					arr[tmp][1]++;
				}
				arr[tmp][0] = node.cnt+1;
				que.add(new Node(tmp, node.cnt+1));
			}
			
			//*2로 이동
			tmp = node.now*2;
			if(tmp>=0 && tmp<=200000 && arr[tmp][0]>=node.cnt+1) {
				if(arr[tmp][0]==node.cnt+1) {
					arr[tmp][1]++;
				}
				arr[tmp][0] = node.cnt+1;
				que.add(new Node(tmp, node.cnt+1));
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		//경로 초기화
		arr = new int[200001][2];
		for (int i = 0; i <= 200000; i++) {
			arr[i][0] = 200000;
			arr[i][1] = 1;
		}
		arr[N][0] = 0;
		
		que.add(new Node(N, 0));
		start();
		
		System.out.println(arr[K][0]);
		System.out.println(arr[K][1]);
	}
}