import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	static String[] arr;
	
	private static String makeTree() {
		StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            String ch = arr[i];
            switch (ch) {
                case "+":
                case "-":
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.append(stack.pop());
                    }
                    stack.push(ch);
                    break;
                case "*":
                case "/":
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        postfix.append(stack.pop());
                    }
                    stack.push(ch);
                    break;
                case "(":
                    stack.push(ch);
                    break;
                case ")":
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    postfix.append(ch);
                    break;
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = br.readLine().split("");
		
		System.out.println(makeTree());
	}
}