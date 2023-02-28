import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int[] arr;
	static int[] sumarr;
	static int sum;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static void sum(int start, int end) throws IOException {
		if(start-2>=0)
			sum = sumarr[end-1]-sumarr[start-2];
		else
			sum = sumarr[end-1];
		
		bw.append(sum+"\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		arr = new int[N];
		sumarr = new int[N];
		
		str = br.readLine().split(" ");
		sumarr[0] = Integer.parseInt(str[0]);
		arr[0] = Integer.parseInt(str[0]);
		for(int i=1; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
			sumarr[i] = sumarr[i-1]+arr[i];
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			sum(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		}
		
		bw.flush();
		bw.close();
	}
}
