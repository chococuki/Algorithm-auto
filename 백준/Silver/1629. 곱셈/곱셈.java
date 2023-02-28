import java.util.Scanner;

public class Main {
	private static long div(long A, long B, int C) {
		if(B==1) {
			return A%C;
		} if (B==2) {
			return (A*A)%C;
		}
		
		long tmp;
		long d = div(A, B/2, C);
		
		if(B%2==0) {
			tmp = (d*d)%C;
		} else {
			tmp = (((d*d)%C)*A)%C;
		}
		
		return tmp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		System.out.println(div(A%C, B, C));
		
	}
}