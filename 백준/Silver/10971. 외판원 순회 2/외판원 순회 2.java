import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int min = 10000000;
	static int[][] arr;
	static int[] visit;
	
	private static void move(int from, int count, int sum, int[] visit) {
		if(count==N) {
			if(arr[from][visit[0]]==0)
				return;
			sum += arr[from][visit[0]];
			min = Math.min(min, sum);
			
/*			for(int i=0; i<N; i++) {
				System.out.print(visit[i]+" ");
			}
			System.out.println("----"+min);*/
			return;
		}
		
		for(int to=0; to<N; to++) {
			boolean is = true;
			for(int i=0; i<=count; i++) {
				if(to==visit[i])
					is =false;
			}
			
			if(is) {
				visit[count]=to;
				if(arr[from][to]==0) {
					return;
				}
				move(to, count+1, sum+arr[from][to], visit);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		String[] str;
		for(int to=0; to<N; to++) {
			str = br.readLine().split(" ");
			for(int from=0; from<N; from++) {
				arr[to][from] = Integer.parseInt(str[from]);
			}
		}
		
		visit = new int[N+1];
		for(int i=0; i<N; i++) {
//			System.out.println("start: "+i);
			visit[0]=i;
			move(i, 1, 0, visit);
		}
		
		System.out.println(min);
	}
}
