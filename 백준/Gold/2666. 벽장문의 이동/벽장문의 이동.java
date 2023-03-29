import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N,left,right,totalStep;
	static int[] number;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());
		
		totalStep = Integer.parseInt(br.readLine());
		number = new int[totalStep];
		
		for(int i = 0; i < totalStep; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}
		
		move(0,left,right,0);
		
		System.out.println(result);
	}
	
	public static void move(int step, int le, int ri, int sum) {
		
		if(step == totalStep) {
			result = Math.min(result, sum);
			
			return;
		}
		
		//왼쪽 문을 가져올 때
		move(step+1,number[step],ri,sum+Math.abs(le-number[step]));
		
		//오른쪽 문을 가져올 때
		move(step+1,le,number[step],sum+Math.abs(ri-number[step]));
		
		
		return;
	
	}

}