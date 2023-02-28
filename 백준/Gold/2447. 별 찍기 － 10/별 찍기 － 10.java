import java.util.Scanner;

public class Main {
	static String[][] str;
	static int N;
	
	public static void make(int startX, int startY, int endX, int endY) {
		if(startX==endX) {
			return;
		}
		int tmpX = (endX-startX)/3;
		int tmpY = (endY-startY)/3;
		if((endX-startX)%3==1 && (endY-startY)%3==1) {
			for(int i=(startX+tmpX); i<(endX-tmpX); i++) {
				for(int j=(startY+tmpY); j<(endY-tmpY); j++) {
					str[i][j] = "*";
				}
			}
		} else {
			make(startX, startY, startX+tmpX, startY+tmpY);
			make(startX+tmpX, startY, endX-tmpX, startY+tmpY);
			make(endX-tmpX, startY, endX, startY+tmpY);
			make(startX, startY+tmpY, startX+tmpX, endY-tmpY);
			make(endX-tmpX, startY+tmpY, endX, endY-tmpY);
			make(startX, endY-tmpY, startX+tmpX, endY);
			make(startX+tmpX, endY-tmpY, endX-tmpX, endY);
			make(endX-tmpX, endY-tmpY, endX, endY);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		str = new String[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				str[i][j] = " ";
			}
		}
		
		make(0, 0, N, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(str[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}