import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		int up = Integer.parseInt(str[0]);
		int down = Integer.parseInt(str[1]);
		int finish = Integer.parseInt(str[2]);
		
		int day = 0;
		int now = 0;
		if((finish-up)>0) {
			day = (finish-up)/(up-down);
			now = day*(up-down);
		}
		
		while(!(now>=finish)) {
			day++;
			now += up;
			if(now>=finish) {
				break;
			}
			now -= down;
		}
		
		
		
		System.out.println(day);
	}
}