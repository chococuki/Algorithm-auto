import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] result;
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(br.readLine());
		result = new int[N+1];
		
		String[] str = br.readLine().split(" ");
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(str[i-1]);
		}
		
		stack.push(N);
		for(int i=N-1; i>=1; i--) {
			if(stack.isEmpty()) {
				stack.push(i);
			} else {
//				System.out.println(i+" "+stack.peek());
				while(arr[stack.peek()]<arr[i]) {
					result[stack.pop()] = i;
					if(stack.isEmpty()) break;
				}
				stack.push(i);
			}
		}
		
		for(int i=1; i<=N; i++) {
			sb.append(result[i]+" ");
		}
		System.out.println(sb.toString());
	}
}
