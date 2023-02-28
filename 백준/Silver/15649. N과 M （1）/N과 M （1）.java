import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] tmp;
	static int N, M;
	
	private static void make(int count, int[] arr) {
		if(count == M) {
			for(int i=0; i<M; i++) {
				sb.append(tmp[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			boolean is = true;
			for(int j=0; j<count; j++) {
				if(arr[j]==i)
					is = false;
			}
			if(is) {
				arr[count] = i;
				make(count+1, arr);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		tmp = new int[M+1];
		
		make(0, tmp);
		System.out.println(sb);
	}
}
