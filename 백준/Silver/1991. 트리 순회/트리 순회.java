import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Deque<String> que = new LinkedList<>();
	static Stack<String> stack = new Stack<>();
	static Map<String, Child> map = new LinkedHashMap<>();
	static StringBuilder sb;
	
	static class Child {
		String left;
		String right;
		public Child(String left, String right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	
	public static String case1() {
		sb = new StringBuilder();
		stack.push("A");
		
		String cur, left, right;
		while(!stack.isEmpty()) {
			cur = stack.pop();
			sb.append(cur);
			left = map.get(cur).left;
			right = map.get(cur).right;
			
			if(!right.equals(".")) stack.push(right);
			if(!left.equals(".")) stack.push(left);
		}
		return sb.toString();
	}
	
	public static String case2() {
		sb = new StringBuilder();

		case2plus("A");
		
		return sb.toString();
	}
	
	public static void case2plus(String cur) {
		if(!map.get(cur).left.equals(".")) {
			case2plus(map.get(cur).left);
		}
		sb.append(cur);
		if(!map.get(cur).right.equals(".")) {
			case2plus(map.get(cur).right);
		}
	}
	
	public static String case3() {
		sb = new StringBuilder();

		case3plus("A");
		
		return sb.toString();
	}
	
	public static void case3plus(String cur) {
		if(!map.get(cur).left.equals(".")) {
			case3plus(map.get(cur).left);
		}
		if(!map.get(cur).right.equals(".")) {
			case3plus(map.get(cur).right);
		}
		sb.append(cur);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String parent = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			
			map.put(parent, new Child(left, right));
		}
		
		System.out.println(case1());
		System.out.println(case2());
		System.out.println(case3());
		
	}
}
