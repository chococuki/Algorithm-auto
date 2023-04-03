import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] result = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Queue<Integer> tmp = new LinkedList<>();
				if(graph[i][j] == 1) {
					result[i][j] = 1;
					tmp.add(j);
				}
				
				while(!tmp.isEmpty()) {
					int now = tmp.poll();
					
					for (int k = 0; k < N; k++) {
						if(graph[now][k]==1 && result[i][k]==0) {
							result[i][k]=1;
							tmp.add(k);
						}
					}
					
					
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]+" ");
			}sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}