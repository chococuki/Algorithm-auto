import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int min;
	static int[] sin;
	static int[] sun;
	static int[] food;
	
	private static void make(int cnt, int cs, int start) {
		if(cs==cnt) {
			cook(cnt);
			return;
		}
		
		for(int i=start; i<N; i++) {
//			System.out.println("food["+cnt+"]= "+i +"\tstart: "+start);
			food[cnt] = i;
			make(cnt+1, cs, i+1);
//			System.out.println("-------------------");
		}
	}
	
	private static void cook(int cnt) {
		int mulsin=1;
		int sumsun=0;
		
		for(int i=0; i<cnt; i++) {
			mulsin *= sin[food[i]];
			sumsun += sun[food[i]];
		}
		
//		for(int i=0; i<cnt; i++) {
//			System.out.print(sin[food[i]]+" ");
//		}
//		System.out.println();
//		for(int i=0; i<cnt; i++) {
//			System.out.print(sun[food[i]]+" ");
//		}
//		System.out.println();
		
		
//		System.out.println("sum "+Math.abs(mulsin-sumsun));
		min = Math.min(min, Math.abs(mulsin-sumsun));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sin = new int[N];
		sun = new int[N];
		food = new int[N];
		
		String[] str;
		str = br.readLine().split(" ");
		sin[0] = Integer.parseInt(str[0]);
		sun[0] = Integer.parseInt(str[1]);
		
		min = Math.abs(sin[0]-sun[0]);
		
		for(int i=1; i<N; i++) {
			str = br.readLine().split(" ");
			sin[i] = Integer.parseInt(str[0]);
			sun[i] = Integer.parseInt(str[1]);
		}
		
		for(int cnt=1; cnt<=N; cnt++) {
			make(0, cnt, 0);
		}
		
		System.out.println(min);
	}
}
