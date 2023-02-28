import java.util.Scanner;

public class Main {
	static String[][] str;
	static StringBuilder sb = new StringBuilder();
	
	public static void make(int startX, int startY, int count) {
		if(count==0) {
			str[startX][startY] = "*";
			str[startX+1][startY-1] = "*";
			str[startX+1][startY+1] = "*";
			str[startX+2][startY-2] = "*";
			str[startX+2][startY-1] = "*";
			str[startX+2][startY] = "*";
			str[startX+2][startY+1] = "*";
			str[startX+2][startY+2] = "*";
			return;
		} else {
			make(startX, startY, count-1);
			make(startX+3*((int)Math.pow(2, count-1)), startY+3*((int)Math.pow(2, count-1)), count-1);
			make(startX+3*((int)Math.pow(2, count-1)), startY-3*((int)Math.pow(2, count-1)), count-1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		
		int count=0;
		int tmp = N/3;
		while(tmp!=1) {
			count++;
			tmp /= 2;
		}
		
		int c=5;
		for(int i=0; i<count; i++) {
			c = c*2+1;
		}

		str = new String[N][c];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<c; j++) {
				str[i][j] = " ";
			}
		}
		
		make(0, N-1, count);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<c; j++) {
				sb.append(str[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}