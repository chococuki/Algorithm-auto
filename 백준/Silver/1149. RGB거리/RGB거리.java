import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[T+1][3];
		for(int i=1; i<=T; i++) {
			String[] tmp = br.readLine().split(" ");
			cost[i][0] = Integer.parseInt(tmp[0]);
			cost[i][1] = Integer.parseInt(tmp[1]);
			cost[i][2] = Integer.parseInt(tmp[2]);
		}
		
		int[][] road = new int[T+1][3];
		
		road[1][0] = cost[1][0];
		road[1][1] = cost[1][1];
		road[1][2] = cost[1][2];
		
		for(int i=2; i<=T; i++) {
			road[i][0] = Math.min(road[i-1][1], road[i-1][2])+cost[i][0];
			road[i][1] = Math.min(road[i-1][0], road[i-1][2])+cost[i][1];
			road[i][2] = Math.min(road[i-1][0], road[i-1][1])+cost[i][2];
		}
		
		int result = road[T][0];
		for(int i=0; i<3; i++) {
			result = Math.min(road[T][i], result);
		}
		System.out.println(result);
	}
}
