import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<String> left = new Stack<>(); 
		Stack<String> right = new Stack<>();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			String[] str = br.readLine().split("");
			
			for(int i=0; i<str.length; i++) {
				switch(str[i]) {
				case "<":
					if(!left.isEmpty()) {
						right.push(left.pop());
					}
					break;
				case ">":
					if(!right.isEmpty()) {
						left.push(right.pop());
					}
					break;
				case "-":
					if(!left.isEmpty()) {
						left.pop();
					}
					break;
				default:
					left.push(str[i]);
					break;
				}
			}
			while(!left.isEmpty()) {
				right.push(left.pop());
			}
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
