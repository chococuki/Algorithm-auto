import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static String[][] str;
	
	public static void zip(int startX, int startY, int endX, int endY) {
		//System.out.println(startX+" "+startY+" "+endX+" "+endY);
		if(startX==endX) return;
		
		String tmp = str[startX][startY];
		boolean iseq = true;
		for(int i=startX; i<endX; i++) {
			for(int j=startY; j<endY; j++) {
				if(!str[i][j].equals(tmp)) {
					iseq = false;
					break;
				}
			}
			if(!iseq) {
				break;
			}
		}
		if(iseq) sb.append(tmp);
		else {
			int tx = (endX-startX)/2;
			int ty = (endY-startY)/2;
					
			sb.append("(");
			zip(startX, startY, startX+tx, startY+ty);
			zip(startX, startY+ty, startX+tx, endY);
			zip(startX+tx, startY, endX, startY+ty);
			zip(startX+tx, startY+ty, endX, endY);
			sb.append(")");
		}
		
		
	};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		str = new String[N][N];
		for(int i=0; i<N; i++) {
			str[i] = br.readLine().split("");
		}
		
		zip(0, 0, N, N);
		
		System.out.println(sb);
	}
}