import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> arr = new ArrayList<>();
	
	public static void push(int n) {
		arr.add(n);
	}
	
	public static int pop() {
		if(arr.isEmpty())
			return -1;
		else {
			int result = arr.get(size()-1);
			arr.remove(size()-1);
			return result;
		}
	}
	
	public static int size() {
		return arr.size();
	}
	
	public static int empty() {
		if(arr.isEmpty())
			return 1;
		else
			return 0;
	}
	
	public static int top() {
		if(arr.isEmpty())
			return -1;
		return arr.get(size()-1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] str;
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			
			int result;
			switch (str[0]) {
				case "push":
					push(Integer.parseInt(str[1]));
					break;
				case "pop":
					System.out.println(pop());
					break;
				case "size":
					System.out.println(size());
					break;
				case "empty":
					System.out.println(empty());
					break;
				case "top":
					System.out.println(top());
					break;
				default:
					break;
			}
		}
	}
}
