import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		int num = Integer.parseInt(tmp[0]);
		int T = Integer.parseInt(tmp[1]);
		
		tmp = br.readLine().split(" ");
		int group = Integer.parseInt(tmp[0]);
		int one = Integer.parseInt(tmp[1]);
		int result = 0;
		
		for(int i=0; i<T-1; i++) {
			tmp = br.readLine().split(" ");
			int tmpGroup = Integer.parseInt(tmp[0]);
			int tmpOne = Integer.parseInt(tmp[1]);
			
			if(tmpGroup < group) group = tmpGroup;
			if(tmpOne < one) one = tmpOne;
		}
		
		if(group/6.0 < one) {
			if((num/6+1)*group < ((num/6)*group + (num%6)*one)) {
				result = (num/6+1)*group;
			} else {
				result = (num/6)*group + (num%6)*one;
			}
		} else {
			result = num*one;
		}

		System.out.println(result);
	}
}
