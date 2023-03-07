import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		StringTokenizer st = new StringTokenizer(str, "+-");
		StringTokenizer st2 = new StringTokenizer(str, "0123456789");
		
		Queue<Object> que = new LinkedList<>();
		
		while(st2.hasMoreTokens()) {
			que.add(Integer.parseInt(st.nextToken()));
			que.add(st2.nextToken());
		}
		que.add(Integer.parseInt(st.nextToken()));
		
		int sum=(int)que.poll(), tmp=0;
		
		boolean minus = false;
		while(!que.isEmpty()) {
			Object obj = que.poll();
			
			if(minus) {
				tmp += (int)que.poll();
			} else {
				if(obj.equals("+")) {
					sum += (int)que.poll();
				} else {
					tmp = (int)que.poll();
					minus = true;
				}
			}
		}
		
		sum -= tmp;
		
		System.out.println(sum);
	}
}