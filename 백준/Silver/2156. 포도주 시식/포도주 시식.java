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
		
		boolean use = true;	//인덱스가 i일때 두번 연속 선택 하였는지
		for (int i = 3; i <= n; i++) {
			if(!use) {
				if((res[i-2]+arr[i]) > (res[i-1]+arr[i])) {
					res[i] = res[i-2]+arr[i];
				} else {
					res[i] = res[i-1]+arr[i];
					use = true;
				}
			} else {
				res[i] = Math.max(res[i-1], res[i-2]+arr[i]);
				use = false;
				
				if((res[i-3]+arr[i-1]+arr[i]) > res[i]) {
					res[i] = res[i-3]+arr[i-1]+arr[i];
					use = true;
				}
			}
		}
		
		System.out.println(res[n]);
	}
}