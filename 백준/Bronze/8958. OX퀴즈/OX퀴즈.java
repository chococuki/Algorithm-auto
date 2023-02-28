import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String[] str;
		
		for(int t=0; t<T; t++) {
			str = br.readLine().split("");
			
			int result=0;
			int cnt=1;
			for(String s:str) {
				if(s.equals("O")) {
					result += cnt;
					cnt++;
				} else {
					cnt=1;
				}
			}
			
			System.out.println(result);
		}
	}
}
