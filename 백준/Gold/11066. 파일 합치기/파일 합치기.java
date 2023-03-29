import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int TC, K;
	static int[] file; // 각 건물을 짓는데 걸리는 시간
	static int[] SumFile;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			file = new int[K+1];
			SumFile = new int[K+1];
			dp = new int[K+1][K+1];

			st = new StringTokenizer(br.readLine());

			// file 병합에 걸리는 시간, 누적합 초기화
			for (int i = 1; i < K + 1; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				SumFile[i] = SumFile[i-1]+file[i];
			}
			
			
			//몇 장씩 묶어서 merge 할 건지
			for(int n = 1; n <= K; n++) {		
				//시작 지점을 한칸 씩 옮겨가면서 
				for(int start = 1; start+n<=K; start++) {
					int end = start+n; //끝지점 정하기
					dp[start][end] = Integer.MAX_VALUE;				
					
					//파일을 합칠 구간에서도 left와 right로 나누어서 병합해줘야한다 
					for(int divide = start; divide < end; divide++) {
						dp[start][end] = Math.min(dp[start][end], 
										 dp[start][divide] + dp[divide+1][end] + SumFile[end]-SumFile[start-1]);
					}
				}
			}
		
			System.out.println(dp[1][K]);
			
		}
	}
}