import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		int[][] cost = new int[T+1][3];
		for(int i=1; i<=T; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] road = new int[T+1][3];
		
		//road[i][j] -> i번째 집에 칠해진 색=j
		road[1][0] = cost[1][0];
		road[1][1] = cost[1][1];
		road[1][2] = cost[1][2];
		
		for(int i=2; i<=T; i++) {
			// i번째에 j 색을 칠하기 위한 최소 비용을 가져옴
			road[i][0] = Math.min(road[i-1][1], road[i-1][2])+cost[i][0];
			road[i][1] = Math.min(road[i-1][0], road[i-1][2])+cost[i][1];
			road[i][2] = Math.min(road[i-1][0], road[i-1][1])+cost[i][2];
		}
		
		int result = Math.min(Math.min(road[T][0], road[T][1]), road[T][2]);
		
		System.out.println(result);
	}
}