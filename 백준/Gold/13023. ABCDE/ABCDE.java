import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] used;
	static int[] arr;
	static boolean isexist;
	static Map<Integer, List<Integer>> friends = new LinkedHashMap<>();
	
	//랜덤으로 5명을 뽑는 함수
	private static void find(int cnt) {
		if(cnt==5) {
			isexist = true;
			return;
		}
		
		List<Integer> tmp = friends.get(arr[cnt-1]);
		for(int i:tmp) {
			if(isexist) break;	//경우가 존재할경우 종료
			if(!used[i]) {
				used[i] = true;
				arr[cnt] = i;
				find(cnt+1);
				used[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		used = new boolean[N];
		
		//map 초기화
		for(int i=0; i<N; i++) {
			friends.put(i, new LinkedList<>());
		}
		
		//친구 관계 map에 저장
		int a, b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		
		//첫번째 친구 정한후 탐색 시작
		arr = new int[5];
		for(int i=0; i<N; i++) {
			used[i] = true;
			arr[0] = i;
			find(1);
			used[i] = false;
		}
		
		if(isexist) System.out.println(1);
		else System.out.println(0);
	}
}