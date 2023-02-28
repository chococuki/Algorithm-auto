import java.io.*;
import java.util.*;

public class Main {
	static int H, N, M;
	static int dayCount=-1;
	static String[][][] arr;
	static boolean[][][] visited;
	static int[] dm = {1, -1, 0, 0, 0, 0};
	static int[] dn = {0, 0, 1, -1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	
	static Queue<Node> que = new LinkedList<>();
	static Queue<Node> tmpque = new LinkedList<>();
	
	static class Node {
		int h, n, m;
		public Node(int h, int n, int m) {
			this.h = h;
			this.n = n;
			this.m = m;
		}
		@Override
		public String toString() {
			return "[" + h + ", " + n + ", " + m + "]";
		}
		
	}
	
	private static void day(int h, int n, int m) {
		for(int i=0; i<dm.length; i++) {
			int now_m = m+dm[i];
			int now_n = n+dn[i];
			int now_h = h+dh[i];
/*			if(now_m>=0 && now_m<M && now_n>=0 && now_n<N && now_h>=0 && now_h<H)
				System.out.println(now_h+" "+now_n+" "+now_m);
			else
				System.out.println("f");*/
			if(now_m>=0 && now_m<M && now_n>=0 && now_n<N && now_h>=0 && now_h<H
					&& arr[now_h][now_n][now_m].equals("0") && !visited[now_h][now_n][now_m]) {
				arr[now_h][now_n][now_m] = "1";
				Node node = new Node(now_h, now_n, now_m);
				que.add(node);
//				System.out.println("push : "+node);
				
			}
		}
	}
	
	private static boolean check() {
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
//					System.out.print(arr[h][n][m]+" ");
					if(arr[h][n][m].equals("0")) {
						return false;
					}
				}
//				System.out.println();
			}
//			System.out.println();
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		
		arr = new String[H][N][M];
		visited = new boolean[H][N][M];
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				str = br.readLine().split(" ");
				for(int m=0; m<M; m++) {
					arr[h][n][m]=str[m];
					if(str[m].equals("1")) {
						Node node = new Node(h, n, m);
						visited[h][n][m]=true;
						que.add(node);
					}
				}
			}
		}
		
		while(!que.isEmpty()) {
			dayCount++;
//			System.out.println(dayCount);
			tmpque = new LinkedList<>(que);
			que = new LinkedList<>();
			while(!tmpque.isEmpty()) {
				Node node = tmpque.poll();
//				System.out.println("poll : "+node);
				day(node.h, node.n, node.m);
//				System.out.println();
			}
			//System.out.println();
			check();
			//System.out.println();
		}
		
		if(check()) {
			System.out.println(dayCount);
		} else {
			System.out.println(-1);
		}
	}
}