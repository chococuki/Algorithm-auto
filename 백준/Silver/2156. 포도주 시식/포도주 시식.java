import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n+1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		
		//기본 초기화
		int[] res = new int[n+1];
		res[1] = arr[1];
		if(n>=2) res[2] = arr[1] + arr[2];
		
		for (int i = 3; i <= n; i++) {
			res[i] = Math.max((res[i-3]+arr[i-1]+arr[i]), Math.max(res[i-1], res[i-2]+arr[i]));
		}
		
		System.out.println(res[n]);
	}
}