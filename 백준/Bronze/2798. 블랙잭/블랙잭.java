import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] arr;
		int sum;
		int result=0;
		boolean go=true;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		str = br.readLine().split(" ");
		
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		for(int i:arr) {
			for(int j:arr) {
				for(int k:arr) {
					sum = 0;
					if(i != j && j!=k && k!=i) {
						sum = i+j+k;
						if(sum <= M && ((M-result) > (M-sum))) {
							result = sum;
						}
						if(result == M) {
							go=false;
							break;
						}
					}
				}
				if(!go) break;
			}
			if(!go) break;
		}
		
		System.out.println(result);
		
	}
}
