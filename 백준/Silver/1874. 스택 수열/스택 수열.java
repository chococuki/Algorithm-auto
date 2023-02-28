import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		
		
		stack.push(1);
		sb.append("+\n");
//		System.out.println("+1");
		
		int input;
		int now = 2;
		int cnt=0;
		boolean finish = true;
		while(true) {
			input = Integer.parseInt(br.readLine());
			
			while(now<=input) {
				stack.push(now);
//				System.out.println("+"+now+"");
				sb.append("+\n");
				now++;
			}
			if(stack.peek()==input) {
//				System.out.println("-"+stack.peek()+"");
				sb.append("-\n");
				stack.pop();
				cnt++;
			}
			if(!stack.isEmpty() && stack.peek()>=input) {
				finish = false;
				break;
			}
			if(cnt==N) {
				break;
			}
		}
		
		if(finish) {
			System.out.println(sb.toString());
		} else {
			System.out.println("NO");
		}

	}

}
