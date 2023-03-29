import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, table[][], result;
	
	//dfs
	private static void find(int now, int price) {
		for (int i = now; i <= N; i++) {
			//상담이 퇴사전에 끝날경우
			if(i+table[i][0] <= N+1) {
				result = Math.max(result, price+table[i][1]);
				find(i+table[i][0], price+table[i][1]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		table = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			table[i][0] = Integer.parseInt(st.nextToken());
			table[i][1] = Integer.parseInt(st.nextToken());
		}
		
		find(1, 0);
		
		System.out.println(result);
	}
}