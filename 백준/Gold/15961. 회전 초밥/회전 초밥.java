import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] rail = new int[N];
		for (int i = 0; i < N; i++) {
			rail[i] = Integer.parseInt(br.readLine());
		}
		
		int[] susi = new int[d+1];
		susi[c]++;
		
		int count = 1;
		
		for (int i = 0; i < k; i++) {
			if(++susi[rail[i]] == 1) count++;
		}
		
		int result = count;
		
		for (int i = k; i < (N+k); i++) {
			if(++susi[rail[i%N]] == 1) count++;
			if(--susi[rail[(i-k)%N]] == 0) count--;
			result = Math.max(result, count);
		}
		
		System.out.println(result);
	}
}