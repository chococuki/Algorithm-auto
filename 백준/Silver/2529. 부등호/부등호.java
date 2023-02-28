import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static int[] arr;
	static String[] str;
	
	private static String max() {
		int start = 9;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		stack.push(start--);
		for(int i=0; i<k; i++) {
			if(str[i].equals(">")) {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				stack.push(start--);
			} else {
				stack.push(start--);
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	private static String min() {
		int start = 0;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		stack.push(start++);
		for(int i=0; i<k; i++) {
			if(str[i].equals("<")) {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				stack.push(start++);
			} else {
				stack.push(start++);
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		str = new String[k];
		arr = new int[k+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		
		for(int i=0; i<k; i++) {
			str[i] = st.nextToken();
		}
		
		System.out.println(max());
		System.out.println(min());
	}
}