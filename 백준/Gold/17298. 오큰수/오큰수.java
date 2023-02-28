import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Stack<Integer> tmp = new Stack<>();
		Stack<Integer> stack = new Stack<>();
		List<Integer> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			stack.push(Integer.parseInt(str[i]));
		}
		
		int max = 0;
		int now;
		while(!stack.isEmpty()) {
//			System.out.println(1);
			now = stack.pop();
			if(tmp.isEmpty()) {
				list.add(-1);
				tmp.push(now);
			}
			else {
				if(now<tmp.peek()) {
					list.add(tmp.peek());
					tmp.push(now);
				} else {
					while(!tmp.isEmpty()) {
//						System.out.println(tmp.size());
						tmp.pop();
						if(tmp.isEmpty()) {
							list.add(-1);
							tmp.add(now);
							break;
						}
						if(now<tmp.peek()) {
							list.add(tmp.peek());
							tmp.push(now);
							break;
						}
					}
				}
			}
		}
		
		Collections.reverse(list);
		
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i)+" ");
		}
		System.out.println(sb.toString());
	}
}
