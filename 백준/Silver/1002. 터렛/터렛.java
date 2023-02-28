import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt((String) br.readLine());
		
		for(int i=0; i<T; i++) {
			String[] tmp = br.readLine().split(" ");
			
			int x1= Integer.parseInt(tmp[0]);
			int y1= Integer.parseInt(tmp[1]);
			int r1= Integer.parseInt(tmp[2]);
			
			int x2= Integer.parseInt(tmp[3]);
			int y2= Integer.parseInt(tmp[4]);
			int r2= Integer.parseInt(tmp[5]);
			
			double d = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
			
			int result = 0;
			if((r1==r2) && (x1==x2) & (y1==y2)) {
				result = -1;
			} else if ((r1+r2) == d || Math.abs(r1-r2) == d) {
				result = 1;
			} else if (((r1+r2) > d) && ((d+r2) > r1) && ((d+r1) > r2)) {
				result = 2;
			}
			
			System.out.println(result);
			
		}
	}
}