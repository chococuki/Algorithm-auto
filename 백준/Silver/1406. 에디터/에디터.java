import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<String> left = new Stack<>(); 
		Stack<String> right = new Stack<>();
		
		String[] str = br.readLine().split("");
		for(String s:str) {
			left.push(s);
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			switch(str[0]) {
			case "L":
				if(!left.isEmpty()) {
					right.push(left.pop());
				}
				break;
			case "D":
				if(!right.isEmpty()) {
					left.push(right.pop());
				}
				break;
			case "B":
				if(!left.isEmpty()) {
					left.pop();
				}
				break;
			case "P":
				left.push(str[1]);
				break;
			}
		}
		while(!left.isEmpty()) {
			right.push(left.pop());
		}
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		System.out.println(sb.toString());
	}
}
