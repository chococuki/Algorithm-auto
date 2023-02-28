import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int in = Integer.parseInt(br.readLine());
		
		long number = 1;
		
		for(int i=0; i<100000; i++) {
			if(in == 1) {
				System.out.println(1);
				break;
			}
			
			if(number >= in) {
				System.out.println(i);
				break;
			}
			number += 6*i;
		}

	}

}
