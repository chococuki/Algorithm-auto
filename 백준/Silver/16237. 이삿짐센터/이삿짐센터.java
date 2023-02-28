import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		int[] boxs = new int[6];
		for(int i=1; i<=5; i++) {
			boxs[i] = Integer.parseInt(str[i-1]);
		}
		
		int result = boxs[5];
		
		while(boxs[4] > 0) {
			result += 1;
			if(boxs[1]>0) {
				boxs[1]--;
			}
			boxs[4]--;
		}
		
		while(boxs[3] > 0) {
			result += 1;
			if(boxs[2]>0) {
				boxs[2]--;
			} else if (boxs[1] > 0){
				boxs[1] -= 2;
			}
			boxs[3]--;
		}
		
		while(boxs[2] > 0) {
			result += 1;
			if(boxs[2]>=2) {
				boxs[2] -= 2;
				if(boxs[1]>0) {
					boxs[1]--;
				}
			} else {
				boxs[2]--;
				if(boxs[1]>0) {
					boxs[1] -= 3;
				}
			}
		}
		
		if(boxs[1] > 0) {
			if(boxs[1]%5 != 0)
				result ++;
			result += boxs[1]/5;
		}
		
		System.out.println(result);
		
		
	}
}