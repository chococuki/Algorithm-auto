import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	//
	private static void run(String[] commands, Deque<Integer> dq) {
		boolean front = true;	//배열의 뒤집힘 여부
		boolean error = false;	//에러 발생여부
		
		for (String command : commands) {
			if(command.equals("R")) front = !front;
			else if(command.equals("D")) {
				if(dq.isEmpty()) {	//비어있을때 D입력시 에러
					error = true;
					break;
				}
				else if(front) dq.pollFirst();
				else dq.pollLast();
			}
		}
		
		//결과값 출력
		if(error) sb.append("error\n");
		else {
			sb.append("[");
			if(!dq.isEmpty()) {
				while(dq.size()>1)
				if(front) {
					sb.append(dq.pollFirst()+",");
				} else {
					sb.append(dq.pollLast()+",");
				}
				sb.append(dq.poll());
			}
			sb.append("]\n");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Deque<Integer> dq = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			
			String[] commands = br.readLine().split("");
			
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), "[,]");
			
			for (int i = 0; i < n; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			
			run(commands, dq);
			
		}
		System.out.println(sb.toString());
	}
}