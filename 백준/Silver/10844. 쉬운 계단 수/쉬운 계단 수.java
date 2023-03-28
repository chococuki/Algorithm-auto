import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 초기화
		int[][] res = new int[N+1][10];
		for (int i = 1; i <= 9; i++) {
			res[1][i] = 1;
		}
		
		//각 자리수 나올 경우 
		for (int i = 2; i <= N; i++) {
			res[i][0] = res[i-1][1];
			res[i][9] = res[i-1][8];
			
			for (int j = 1; j <= 8; j++) {
				res[i][j] = (res[i-1][j-1] + res[i-1][j+1])%1000000000;
			}
		}
		
		//결과 값
		int result = 0;
		for (int i = 0; i <= 9; i++) {
			result += res[N][i];
			result %= 1000000000;
		}
		
		System.out.println(result);
	}
}