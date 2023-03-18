import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] result;
	static List<Integer> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	private static void dfs(int cnt) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			result[cnt] = list.get(i);
			dfs(cnt+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		result = new int[M];
		
		for (int i = 0; i < N; i++) {
			list.add(sc.nextInt());
		}
		
		Collections.sort(list);
		
		dfs(0);
		
		System.out.println(sb.toString());
	}
}
