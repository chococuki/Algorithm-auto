import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		String numbers = st.nextToken();

		int count = 0;
		Stack<Character> stack = new Stack<>();
		for(char number: numbers.toCharArray()) {
			if(stack.isEmpty() || count == K) {
				stack.push(number);
				continue;
			}

			while(!stack.isEmpty() && count < K) {
				if(stack.peek() < number) {
					stack.pop();
					count++;
				} else {
					break;
				}
			}
			stack.push(number);
		}
		
		while(count != K) {
			stack.pop();
			count++;
		}

		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.reverse());
	}
}