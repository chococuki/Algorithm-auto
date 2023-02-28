import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt((String) br.readLine());
		
		for(int i=0; i<T; i++) {
			String[] citys = br.readLine().split(" ");
			
			int a = Integer.parseInt(citys[0]);
			int b = Integer.parseInt(citys[1]);

			
			double div1 = 1.0;
			for(int j=0; j<(b-a); j++) {
				div1 *= (b-j);
				div1 /= (j+1);
			}
			
			System.out.println((int)div1);
		}
		
		

	}
}
