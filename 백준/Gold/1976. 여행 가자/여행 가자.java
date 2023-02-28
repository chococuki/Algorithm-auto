import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	
	//a, b 연결 함수
	private static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap==bp) return;
		else {
			if(ap<bp) arr[bp] = ap;
			else arr[ap] = bp;
		}
	}
	
	//a, b가 연결되어있는지 확인 함수
	private static int find(int n) {
		if(arr[n]==n) return n;
		else return find(arr[n]);
	}
	
	//from -> to 가능한지 확인하는 함수
	private static boolean check(int from, int to) {
		if(find(from)==find(to)) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		
		//arr 초기화
		arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i]=i;
		}
		
		//경로 저장
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				if(st.nextToken().equals("1")) {
					union(i, j);
				}
			}
		}
		
		//계획이 가능한지 확인
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to;
		boolean cango=true;
		do {
			to = Integer.parseInt(st.nextToken());
			if(!check(from, to)) {
				cango=false;
				break;
			}
			from = to;
		} while(st.hasMoreTokens());
		
		if(cango) System.out.println("YES");
		else System.out.println("NO");
	}
}