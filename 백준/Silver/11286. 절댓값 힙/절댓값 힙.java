import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> plus = new PriorityQueue<>();
		PriorityQueue<Integer> minus = new PriorityQueue<>(Collections.reverseOrder());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int num;
		for(int i=0; i<N; i++) {
			num = Integer.parseInt(br.readLine());
			
			if(num == 0) {
				if(plus.isEmpty() && minus.isEmpty()) {
					sb.append("0");
				} else if(!plus.isEmpty() && !minus.isEmpty()) {
					if (Math.abs(plus.peek()) < Math.abs(minus.peek())) {
						sb.append(plus.poll());
					} else {
						sb.append(minus.poll());
					}
				} else if (plus.isEmpty()) {
					sb.append(minus.poll());
				} else {
					sb.append(plus.poll());
				}
				sb.append("\n");
			} else if(num > 0) {
				plus.offer(num);
			} else {
				minus.offer(num);
			}
		}
		
		
		System.out.println(sb.toString());
		
	}
}
