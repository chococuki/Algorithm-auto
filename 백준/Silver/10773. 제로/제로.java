import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> que = new Stack<>();
		
		int K = Integer.parseInt(br.readLine());
		
		int n;
		for(int i=0; i<K; i++) {
			n = Integer.parseInt(br.readLine());
			if(n != 0) {
				que.add(n);
			} else if(!que.isEmpty()){
				que.pop();
			}
		}
		
		int sum=0;
		for(int i:que) {
			sum += i;
		}
		
		System.out.println(sum);
	}
}
