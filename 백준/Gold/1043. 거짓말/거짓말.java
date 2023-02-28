import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static boolean[] know;
	static List<Integer> truth = new LinkedList<>();
	static List<List<Integer>> group = new LinkedList<>();
	
	private static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap==bp) return;
		else {
			if(ap<bp) arr[ap]=bp;
			else arr[bp]=ap;
		}
	}
	
	private static int find(int n) {
		if(arr[n]==n) return n;
		else return find(arr[n]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr=new int[N+1];
		know = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = i;
		}
		
		//진실을 아는 사람 저장
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for(int i=0; i<t; i++) {
			truth.add(Integer.parseInt(st.nextToken()));
		}
		
		//그룹 트리 생성, 저장
		List<Integer> tmp;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			tmp = new LinkedList<>();
			st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			tmp.add(a);
			int b;
			while(st.hasMoreTokens()) {
				b = Integer.parseInt(st.nextToken());
				tmp.add(b);
				union(a, b);
				a=b;
			}
			group.add(tmp);
		}
		
		//진실을 아는 사람의 부모를 진실을 아는 사람으로 저장
		for(int i:truth) {
			know[i]=true;
			know[find(i)]=true;
		}
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(know));
//		System.out.println(group);
		
		//과장 가능한 그룹 찾기
		int cnt=0;
		boolean can;
		for(int i=0; i<M; i++) {
			can=true;
			tmp = group.get(i);
//			System.out.println(tmp);
			for(int j:tmp) {
				if(know[find(j)]) {
//					System.out.println("know: "+j);
					can = false;
					break;
				}
			}
			if(can) cnt++;
		}
		
		System.out.println(cnt);
	}
}