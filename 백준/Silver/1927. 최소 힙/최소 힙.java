import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> que = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int num;
		for(int i=0; i<N; i++) {
			num = Integer.parseInt(br.readLine());
			
			if(num==0) {
				if (que.isEmpty()) {
					sb.append("0");
				} else {
					sb.append(que.poll());
				}
				sb.append("\n");
			} else {
				que.offer(num);
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
