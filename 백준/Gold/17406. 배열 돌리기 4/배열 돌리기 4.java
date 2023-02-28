import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int min = -1;
	static List<Node> list = new ArrayList<>();
	static boolean[] used;
	static int[] dn = {1, 0, -1, 0};
	static int[] dm = {0, 1, 0, -1};
	
	public static class Node {
		int r, c, s;

		public Node(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		
	}
	
	private static void dfs(int cnt, int[][] arr) {
//		print(arr);
		if(cnt==0) {
			check(arr);
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(used[i]) continue;
			
			used[i] = true;
//			System.out.println(list.get(i).r+" "+list.get(i).c+" "+list.get(i).s);
			
			int[][] tmp = new int[N+1][M+1];
			
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					tmp[n][m] = arr[n][m];
				}
			}
			
			dfs(cnt-1, rotate(list.get(i).r, list.get(i).c, list.get(i).s, tmp));
			used[i] = false;
//			System.out.println("<--");
		}
	}
	
	private static int[][] rotate(int r, int c, int s, int[][] arr) {
//		System.out.println(r+" "+c+" "+s);
		while(s>0) {
			int now_n = r-s;
			int now_m = c-s;
			int tmp = arr[r-s][c-s];
			int mode = 0;
			int new_n, new_m;
			do {
				if(now_n+dn[mode] <= r+s && now_n+dn[mode] >= r-s && now_m+dm[mode] <= c+s && now_m+dm[mode] >= c-s) {
					new_n = now_n+dn[mode];
					new_m = now_m+dm[mode];
				} else {
					mode += 1;
					if(mode==4) mode=0;
					new_n = now_n+dn[mode];
					new_m = now_m+dm[mode];
				}
//				System.out.println(arr[now_n][now_m]+" <- "+arr[new_n][new_m]);
				arr[now_n][now_m] = arr[new_n][new_m];
				now_n = new_n;
				now_m = new_m;
			} while(!(now_n==r-s && now_m==c-s));
			arr[r-s][c-s+1] = tmp;
//			print();
			s--;
		}
		
		return arr;
	}
	
	private static void check(int[][] arr) {
		
		for(int n=1; n<=N; n++) {
			int tmp = 0;
			for(int m=1; m<=M; m++) {
				tmp += arr[n][m];
			}
			if(min==-1) {
				min = tmp;
			} else {
				min = Math.min(min, tmp);
			}
//			System.out.println(tmp);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		
		for(int n=1; n<=N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=1; m<=M; m++) {
				arr[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
//		print(arr);
		
		int r, c, s;
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			list.add(new Node(r, c, s));
		}
		
		used = new boolean[list.size()];
		dfs(list.size(), arr);
		
		System.out.println(min);
	}
	
//	public static void print(int[][] arr) {
//		for(int n=1; n<=N; n++) {
//			for(int m=1; m<=M; m++) {
//				System.out.print(arr[n][m]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------");
//	}
}
