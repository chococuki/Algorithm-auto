import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Queue<Integer> que = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		for(int i=1; i<=N; i++) {
			que.add(i);
		}
		
		sb.append("<");
		while(!que.isEmpty()) {
			for(int i=1; i<K; i++)
				que.add(que.poll());
			sb.append(que.poll().toString());
			if(!que.isEmpty()) {
				sb.append(", ");
			} else break;
		}
		sb.append(">");
		
		System.out.println(sb.toString());
	}
}
