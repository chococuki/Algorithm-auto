import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt((String) br.readLine());
		
		int five = 0;
		
		int result = -1;
		int i = 0;
		while (i<15) {
			if ((n-i)<0) {
				break;
			}
			five = (n-i)/5;
			int k = n-five*5;
			
			if (k%3 == 0) {
				result = five + (k/3);
				break;
			}
			i++;
		}
		
		System.out.println(result);
		
	}
}