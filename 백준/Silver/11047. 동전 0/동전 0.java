import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int count = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		
		int[] coins = new int[N];
		for(int i=0; i<N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=N-1; i>=0;) {
			if(K >= coins[i]) {
				K -= coins[i];
				count++;
			} else {
				i--;
			}
			if(K == 0) {
				break;
			}
		}
		
		System.out.println(count);
	}

}