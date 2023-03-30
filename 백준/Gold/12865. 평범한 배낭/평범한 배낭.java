import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input [] = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		int [] W = new int[N+1];
		int [] V = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			String [] in = br.readLine().split(" ");
			int w = Integer.parseInt(in[0]);
			int v = Integer.parseInt(in[1]);
			
			W[i] = w;
			V[i] = v;
			
			
		}
		
		int [][] DP = new int[N+1][K+1];
		
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < K+1; j++) {
				if(j < W[i])DP[i][j] = DP[i-1][j];
				
				else if(j >= W[i])DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-W[i]]+V[i]);
			}
			
		}
		
		System.out.println(DP[N][K]);
		
		
	}

}