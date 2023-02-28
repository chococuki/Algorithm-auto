import java.util.Scanner;

public class Main {
	static int cnt;
	
	private static void find(int N, int r, int c) {
		if(N==0) {
			if(r != 0 && c != 0) {
				cnt++;
			}
			return;
		}
		
		int tmp = (int) Math.pow(2, N-1);
		if(r<tmp) {
			if(c<tmp) {
				find(N-1, r, c);
			} else {
				cnt += Math.pow(tmp, 2);
				find(N-1, r, c-tmp);
			}
		} else {
			if(c<tmp) {
				cnt += Math.pow(tmp, 2)*2;
				find(N-1, r-tmp, c);
			} else {
				cnt += Math.pow(tmp, 2)*3;
				find(N-1, r-tmp, c-tmp);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		find(N, r, c);
		
		System.out.println(cnt);
	}
}