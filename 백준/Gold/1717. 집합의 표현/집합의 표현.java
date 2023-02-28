import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	
	//b의 부모를 a의 부모로 저장
	private static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap==bp) return;
		else {
			if(bp>ap) arr[ap] = bp;
			else arr[bp] = ap;
		}
	}
	
	//최고 부모를 찾는 함수
	private static int find(int n) {
		if(arr[n]==n) return n;
		else {
			return find(arr[n]);
		}
	}
	
	//같은 집합인지 확인하는 함수
	private static String check(int a, int b) {
		if(find(a)==find(b)) return "YES\n";
		else return "NO\n";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	//출력값 저장
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		//arr을 본인값으로 초기화
		for(int i=1; i<=N; i++) {
			arr[i] = i;
		}
		
		int command, a, b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			command = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(command==0) {	//합치는 명령어
				union(a, b);
			} else {	//확인하는 명령어
				sb.append(check(a, b));
			}
		}
		
		System.out.println(sb.toString());
			
	}
}
