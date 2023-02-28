import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr;
	static boolean[] checked = new boolean[10];
	static List<int[]> list = new ArrayList<>();
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		arr = new int[M];
		
		dfs(1, 0);
		
		bw.flush();
		bw.close();
	}
		
		
	public static void dfs(int start, int dept) throws IOException {
		if(dept == M) {
			for(int i: arr) {
				bw.write(Integer.toString(i)+" ");
			}
			bw.write("\n");
			return;
		}
		for(int i=start;i<=N;i++) {
			arr[dept] = i;
			dfs(i+1, dept+1);
		}	
		
	}
}