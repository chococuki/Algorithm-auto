import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int num;
	static int[] switchs;
	
	private static void boy(int start, int cnt) {
		if((start*cnt)>num) {
			return;
		}

		if(switchs[start*cnt]==1) {
			switchs[start*cnt]=0;
		} else {
			switchs[start*cnt]=1;
		}
		boy(start, cnt+1);

	}
	
	private static void girl(int start, int cnt) {
		if((start+cnt)<=num && (start-cnt)>=1) {
			if(switchs[start+cnt]==switchs[start-cnt]) {
				if(switchs[start+cnt]==1) {
					switchs[start+cnt]=0;
					switchs[start-cnt]=0;
				} else {
					switchs[start+cnt]=1;
					switchs[start-cnt]=1;
				}
//				System.out.println(Arrays.toString(switchs));
				girl(start, cnt+1);
			}
		} else {
			return;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine());
		
		switchs = new int[num+1];
		String[] str = br.readLine().split(" ");
		for(int i=1; i<=num; i++) {
			switchs[i] = Integer.parseInt(str[i-1]);
		}
		
		int stu = Integer.parseInt(br.readLine());
		
		for(int i=0; i<stu; i++) {
			str = br.readLine().split(" ");
			
			if(str[0].equals("1")) {
				boy(Integer.parseInt(str[1]), 1);
			} else {
				girl(Integer.parseInt(str[1]), 0);
			}
		}
		
		int cnt=0;
		for(int i=1; i<switchs.length; i++) {
			if(cnt==20) {
				System.out.println();
				cnt=0;
			}
			cnt++;
			System.out.print(switchs[i]+" ");
		}
		
	}
}
