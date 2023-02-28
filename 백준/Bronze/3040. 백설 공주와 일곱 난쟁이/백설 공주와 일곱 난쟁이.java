import java.util.Scanner;

public class Main {
	static int[] arr;
	static int[] tmp;
	static int[] result;
	static int N = 7;
	
	private static void find(int cnt, int start) {
		if(cnt == 7) {
			int sum=0;
			for(int i:tmp) {
				sum += i;
			}
			if(sum==100) {
				for(int i=0; i<7; i++) {
					result[i] = tmp[i];
				}
			}
			return;
		};
		
		for(int i=start; i<9; i++) {
			tmp[cnt] = arr[i];
			find(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		arr = new int[9];
		tmp = new int[7];
		result = new int[7];
		
		for(int i=0; i<9; i++) {
			arr[i] = sc.nextInt();
		}
		
		find(0, 0);
		
		for(int i:result) {
			System.out.println(i);
		}
	}
}