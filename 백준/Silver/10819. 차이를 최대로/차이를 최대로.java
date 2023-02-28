import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, max;
	static int[] arr, res;
	static boolean[] used;
	
	private static void math(int cnt) {
		if(cnt == N) {
			int tmp=0;
			for(int i=0; i<N; i++) {
				if(i+1<N) {
//					System.out.print("|"+res[i]+"-"+res[i+1]+"|");
					tmp += Math.abs(res[i]-res[i+1]);
				}
//				System.out.print("+");
			}
//			System.out.println("  "+tmp);
			max = Math.max(max, tmp);
			
			return;
		}
		
//		System.out.println(Arrays.toString(used));
		for(int i=0; i<N; i++) {
			if(!used[i]) {
				res[cnt] = arr[i];
				used[i] = true;
				math(cnt+1);
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		res = new int[N];
		used = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		math(0);
		
		System.out.println(max);
	}
}