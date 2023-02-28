import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int weight;
	static Queue<Integer> truck = new LinkedList<>();
	static Queue<Integer> bridge = new LinkedList<>();
	static Queue<Integer> time = new LinkedList<>();
	static int cnt=1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int w = Integer.parseInt(str[1]);
		int L = Integer.parseInt(str[2]);

		
		str = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			truck.add(Integer.parseInt(str[i]));
		}
		
		weight += truck.peek();
		bridge.add(truck.poll());
		time.add(cnt);
//		System.out.println(weight);
		
		
		while(!bridge.isEmpty()) {
			cnt++;
//			System.out.println("-----------");
//			System.out.println(cnt);
			
			if((cnt-time.peek()) == w) {
				time.poll();
//				System.out.println("out: "+bridge.peek()+" "+(weight-bridge.peek()));
				weight -= bridge.poll();
			}
			
			if(!truck.isEmpty()) {
				if(weight+truck.peek() <= L) {
					weight += truck.peek();
//					System.out.println("in: "+truck.peek()+" "+weight);
					bridge.add(truck.poll());
					time.add(cnt);
				}
			}
		}
		
		System.out.println(cnt);
		
	}
}
