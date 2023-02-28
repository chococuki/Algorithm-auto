import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt=0;
	static int[][] arr;
	
	//0-가로, 1-대각선, 2-세로
	private static void find(int now_x, int now_y, int mode) {
		if(now_x==N-1 && now_y==N-1) {
			cnt++;
			return;
		}
		
		if(mode==0) {
			if(now_y+1<N && arr[now_x][now_y+1]==0) {
				find(now_x, now_y+1, 0);
				
				if(now_x+1<N && arr[now_x+1][now_y+1]==0 && arr[now_x][now_y+1]==0 && arr[now_x+1][now_y]==0) {
					find(now_x+1, now_y+1, 1);
				}
			}
		} else if(mode==1) {
			if(now_y+1<N && arr[now_x][now_y+1]==0) {
				find(now_x, now_y+1, 0);
				
				if(now_x+1<N && arr[now_x+1][now_y+1]==0 && arr[now_x][now_y+1]==0 && arr[now_x+1][now_y]==0) {
					find(now_x+1, now_y+1, 1);
				}
			}
			if(now_x+1<N && arr[now_x+1][now_y]==0) {
				find(now_x+1, now_y, 2);
			}
		} else {
			if(now_x+1<N && arr[now_x+1][now_y]==0) {
				find(now_x+1, now_y, 2);
				
				if(now_y+1<N && arr[now_x+1][now_y+1]==0 && arr[now_x][now_y+1]==0 && arr[now_x+1][now_y]==0) {
					find(now_x+1, now_y+1, 1);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		find(0, 1, 0);
		
		System.out.println(cnt);
	}
}