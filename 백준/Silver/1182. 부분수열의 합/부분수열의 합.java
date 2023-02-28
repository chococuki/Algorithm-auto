import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, S;
	static int sumCnt;
	static int tmpsum;
	static int[] arr;
	static int[] numbers;
	static boolean[] used;
	
	private static void find(int start, int cnt, int sum) {
		if(sum==S && cnt!=0) sumCnt++;
		if(start>N) return;
		
		for(int i=start; i<N; i++) {
			find(i+1, cnt+1, sum+arr[i]);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		arr = new int[N];
		numbers = new int[N];
		used = new boolean[N];
		
		str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		find(0, 0, 0);
		
		System.out.println(sumCnt);
	}
}