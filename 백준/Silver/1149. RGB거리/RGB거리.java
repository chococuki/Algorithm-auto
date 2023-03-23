
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int minimum = Integer.MAX_VALUE;
		
		int[][]house = new int [N][N];
		int [][] DP = new int [N+1][3];
		
		for(int i = 0; i < N; i++) {
			String [] input = br.readLine().split(" ");
			for(int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(input[j]);
			}
		}
		

		
		for(int i = 1; i < N+1; i++) {
			DP[i][0] = Math.min(DP[i-1][1]+house[i-1][0],DP[i-1][2]+ house[i-1][0]);
			DP[i][1] = Math.min(DP[i-1][0]+house[i-1][1],DP[i-1][2]+ house[i-1][1]);
			DP[i][2] = Math.min(DP[i-1][0]+house[i-1][2],DP[i-1][1]+ house[i-1][2]);
	
		}

		for (int i = 0; i < 3; i++) {
			minimum = Math.min(DP[N][i],minimum);
		}
		
		System.out.println(minimum);
		
	}

}
