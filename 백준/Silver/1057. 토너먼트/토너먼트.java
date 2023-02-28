import java.io.*;

public class Main {
	static int[] nextRound(int[] n) {
		n[0] = (n[0]+1)/2;
		n[1] = (n[1]+1)/2;
		return n;
	}
	
	static boolean roundMatch(int[] n) {
		if((n[0]+1)/2 == (n[1]+1)/2 ) {
			if(n[0]%2 == 1) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		int[] n = {Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])};
		
		if(n[0] > n[1]) {
			int t = n[0];
			n[0] = n[1];
			n[1] = t;
		}
		
		int count = 1;
		while(!roundMatch(n)) {
			count++;
			n = nextRound(n);
		}
		
		System.out.println(count);
		
	}
}
