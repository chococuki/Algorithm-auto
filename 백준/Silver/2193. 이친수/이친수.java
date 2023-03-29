import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long num0 = 0, num1 = 1;
		long tmp;
		for (int i = 2; i <= N; i++) {
			tmp = num0;
			num0 += num1;
			num1 = tmp;
		}
		
		System.out.println(num0+num1);
	}
}