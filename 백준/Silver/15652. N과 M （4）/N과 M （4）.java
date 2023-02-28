import java.io.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr;
	static int N, M;
	
	public static void dfs(int start, int end, int dept) throws IOException {
		if(dept == M) {
			for(int i:arr) {
				bw.write(i+" ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=start; i<=end; i++) {
			arr[dept] = i;
			
			dfs(i, end, dept+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		arr = new int[M];
		dfs(1, N, 0);
		
		bw.flush();
		bw.close();
	}
}
