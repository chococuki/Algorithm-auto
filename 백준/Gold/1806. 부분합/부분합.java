import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int S = Integer.parseInt(str[1]);
		
		String[] tmp = br.readLine().split(" ");
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		
		int sum = arr[0];
		int max = 0;
		int min = 0;
		int count = N;
		boolean isOver = false;
		while(true) {
			if(min==0 && max==0 && sum>=S) {
				isOver = true;
				count = 1;
				break;
			}
			
			if(sum<S) {
				if (max<N-1) {
					sum += arr[++max];
				} else {
					break;
				}
			} else if(sum>=S) {
				if (max == N-1 && min == N-1) {
					break;
				} else {
					sum -= arr[min++];
				}
			}
			if(sum >= S) {
				count = Math.min(count, max-min+1);
				isOver = true;
			}			
		}
		if(!isOver) {
			count = 0;
		} 
		System.out.println(count);
	}
}