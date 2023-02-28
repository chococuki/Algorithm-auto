import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt((String) br.readLine());
		String[] str = br.readLine().split(" ");
		
		int[] arr = new int[n];
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		int[] res = new int[arr.length];
		for(int i=0; i<arr.length; i++) {
			res[i] = 1;
		}

		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j] && res[i] < res[j]+1) {
					res[i] = res[j]+1;
				}
			}
		}
		
		int max=0;
		for(int i=0; i<res.length; i++) {
			max = Math.max(max, res[i]);
		}
		
		System.out.println(max);
	}
}