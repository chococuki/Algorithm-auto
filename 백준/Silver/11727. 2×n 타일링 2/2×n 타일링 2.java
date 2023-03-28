import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] res = new int[n+1];
		res[1] = 1;
		if(n >= 2) res[2] = 3;
		
		for (int i = 3; i <= n; i++) {
			//		세로 블럭		정사각형 or 가로블럭
			res[i] = res[i-1] + res[i-2]*2;
			res[i] %= 10007;
		}
		
		System.out.println(res[n]);
	}
}