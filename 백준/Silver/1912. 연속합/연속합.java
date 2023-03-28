import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] res = new int[n];
		
		res[0] = arr[0];
		
		int result = arr[0];
		for (int i = 1; i < n; i++) {
			res[i] = Math.max(res[i-1]+arr[i], arr[i]);
			result = Math.max(res[i], result);
		}
		
		System.out.println(result);
	}
}
