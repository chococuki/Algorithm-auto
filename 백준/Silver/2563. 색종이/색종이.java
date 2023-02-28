import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int SIZE = 100;
	static final int PSIZE = 10;
	static int[][] board = new int[SIZE][SIZE];
	
	private static void draw(int left, int bottom) {
		for(int x=bottom; x<(bottom+PSIZE); x++) {
			for(int y=left; y<(left+PSIZE); y++) {
				board[x][y] = 1;
			}
		}
	}
	
	private static int get() {
		int sum=0;
		for(int x=0; x<SIZE; x++) {
			for(int y=0; y<SIZE; y++) {
				sum += board[x][y];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int left, bottom;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			left = Integer.parseInt(st.nextToken());
			bottom = Integer.parseInt(st.nextToken());
			
			draw(left, bottom);
			
		}
		System.out.println(get());
		
	}
}
