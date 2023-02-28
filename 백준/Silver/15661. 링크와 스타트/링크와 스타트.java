import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int min=-1;
	static int[][] arr;
	static boolean[] team;
	
	private static void make(int cnt, int start) {
		if(cnt==0) {
			check();
			return;
		}
		
		for(int i=start; i<N; i++) {
			team[i] = true;
			make(cnt-1, i+1);
			team[i] = false;
		}
	}
	
	private static void check() {
		int A=0;
		int B=0;
		
		for(int i=0; i<N; i++) {
			if(team[i]) {
				for(int j=0; j<N; j++) {
					if(team[j]) {
//						System.out.println("a "+i+" "+j+"\t"+arr[i][j]);
						A += arr[i][j];
					}
				}
			} else {
				for(int j=0; j<N; j++) {
					if(!team[j]) {
//						System.out.println("b "+i+" "+j+"\t"+arr[i][j]);
						B += arr[i][j];
					}
				}
			}
		}
		
		if(min == -1) {
			min = Math.abs(A-B);
//			System.out.println("min0: "+(A-B));
		} else {
//			System.out.println("min: "+min);
			min = Math.min(min, Math.abs(A-B));
		}
//		System.out.println("-------------------------");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		team = new boolean[N];
		
		String[] str;
		for(int i=0; i<N; i++) {
			str=br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=1; i<N; i++) {
			make(i,0);
		}
		
		System.out.println(min);
	}
}
