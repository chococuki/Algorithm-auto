import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int cntw=0;
	static int cntb=0;
	
	private static void cut(int startx, int starty, int len) {
		if(len==1) {
			if(arr[startx][starty]==0) cntw++;
			else cntb++;
			return;
		}
		
		
		boolean issame = true;
		int start = arr[startx][starty];
		for(int i=startx; i<startx+len; i++) {
			if(issame) {
				for(int j=starty; j<starty+len; j++) {
					if(arr[i][j] != start) {
						issame = false;
						break;
					}
				}
			}
		}
		
		if(issame) {
			if(start==0) cntw++;
			else cntb++;
		} else {			
			int new_len = len/2;
			cut(startx, starty, new_len);
			cut(startx+new_len, starty, new_len);
			cut(startx, starty+new_len, new_len);
			cut(startx+new_len, starty+new_len, new_len);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0, 0, N);
		
		System.out.println(cntw+"\n"+cntb);
	}
}