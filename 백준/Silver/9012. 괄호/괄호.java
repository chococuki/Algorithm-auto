import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			
			int countA = 0;
			int countB = 0;
			boolean result = true;
			for(int j=0; j<str.length; j++) {
				if(str[j].equals("(")) countA++;
				if(str[j].equals(")")) countB++;
				
				if(countB > countA) {
					result = false;
				}
			}
			if(countA != countB) result=false;
			
			if(result) System.out.println("YES");
			else System.out.println("NO");

		}

	}

}