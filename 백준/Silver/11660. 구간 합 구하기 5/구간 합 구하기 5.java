import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] sum;
	static int[][] arr;
	static int[][] sumarr;
									//2			2			3		4
	private static void find(int cnt, int startX, int startY, int endX, int endY) {
		for(int x=startX; x<=endX; x++) {
			sum[cnt] += (sumarr[x][endY] - sumarr[x][startY-1]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		arr = new int[N+1][N+1];
		sumarr = new int[N+1][N+1];

		
		for(int x=1; x<=N; x++) {
			str = br.readLine().split(" ");
			sumarr[x][1] = Integer.parseInt(str[0]);
			arr[x][1] = Integer.parseInt(str[0]);
			for(int y=2; y<=N; y++) {
				arr[x][y] = Integer.parseInt(str[y-1]);
				sumarr[x][y] = sumarr[x][y-1] +Integer.parseInt(str[y-1]);
			}
		}
		
		sum = new int[M];
		for(int m=0; m<M; m++) {
			str=br.readLine().split(" ");
			find(m, Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
		}
		
		for(int i:sum) {
			System.out.println(i);
		}
	}
}