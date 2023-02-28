import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		long[] road = new long[N-1];
		for(int i=0; i<N-1; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		long[] price = new long[N];
		for(int i=0; i<N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		long result = 0;
		long last_price = price[0];
		int km=0;
		for(int i=1; i<N; i++) {
			km += road[i-1];
			
			if(price[i]<last_price) {
				result += (km*last_price);
				last_price = price[i];
				km = 0;
			}
			
			if(i == N-1) {
				result += (km*last_price);
			}
		}
		
		System.out.println(result);
	}
}
