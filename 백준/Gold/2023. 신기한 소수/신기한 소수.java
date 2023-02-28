import java.util.Scanner;

public class Main {
	static int N;
	static int number;
	static StringBuilder sb = new StringBuilder();
	
	private static void makeNum(String s, int cnt) {
		if(cnt==N) {
			if(prime(s)) {
				sb.append(s+"\n");
			}
		}
		
		for(int i=1; i<10; i++) {
			if(prime(s+i)) {
				makeNum(s+i, cnt+1);
			}
		}
	}
	
	private static boolean prime(String s) {
		number = Integer.parseInt(s);
		
		if(number==1) return false;
		
		for(int i=2; i<=number/2; i++) {
			if(number%i == 0) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		makeNum("", 0);
		
		System.out.print(sb);
	}
}
