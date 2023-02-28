import java.io.*;

public class Main {
	
	static int[][] arr = new int[41][2];
	static int zerosum = 0;
	static int onesum = 0;
	
	static int fibonacci(int n) {
	    if (n == 0) {
	        zerosum++;
	        return 0;
	    } else if (n == 1) {
	        onesum++;
	        return 1;
	    } else if (arr[n][0] != 0){
	    	zerosum += arr[n][0];
	    	onesum += arr[n][1];
	    	return 0;

	    } else {
	        return fibonacci(n-1) + fibonacci(n-2);
	    }
	    
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		arr[34][0] = 3524578;
		arr[34][1] = 5702887;
		arr[35][0] = 5702887;
		arr[35][1] = 9227465;
		
		
		int T = Integer.parseInt((String) br.readLine());
		
		for(int i=0; i<T; i++) {
			zerosum = 0;
			onesum = 0;
			
			int n = Integer.parseInt((String) br.readLine());
			
			fibonacci(n);
			
			arr[n][0] = zerosum;
			arr[n][1] = onesum;
			
			bw.write(arr[n][0]+" "+arr[n][1]+"\n");
		}
		bw.flush();
		bw.close();
	}
}