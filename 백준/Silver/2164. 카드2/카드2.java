import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Queue<Integer> que = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i=1; i<=N; i++) {
			que.add(i);
		}
		
		while(que.size() != 1) {
			que.poll();
			que.add(que.poll());
		}
		
		System.out.println(que.poll());
	}
}