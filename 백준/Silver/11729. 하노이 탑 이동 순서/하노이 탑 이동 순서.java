import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int count;
	
	public static void hannoi(int cnt, int A, int B, int C) {
		if(cnt==0) return;
		
		hannoi(cnt-1, A, C, B);
		count++;
		sb.append(A+" "+C+"\n");
		
		hannoi(cnt-1, B, A, C);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		hannoi(sc.nextInt(), 1, 2, 3);

		System.out.println(count+"\n"+sb);
	}
}