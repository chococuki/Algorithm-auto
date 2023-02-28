import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, min=Integer.MAX_VALUE;
	static int[] population;
	static Map<Integer, List<Integer>> region;
	
	//초기화 함수
	private static void make(int n) {
		population = new int[n+1];
		region = new LinkedHashMap<>();
		
		for(int i=1; i<=n; i++) {
			region.put(i, new ArrayList<>());
		}
	}
	
	//구역을 나누기 시작하는 함수
	static void subset(int cnt, boolean[] isSelected) {
		if (cnt == N + 1) {
			boolean[] visited = new boolean[cnt];
			int check = 0;
			for (int i = 1; i < cnt; i++) {
				if (!visited[i]) {
					dfs(i, visited, isSelected);
					check++;
				}
			}
			if (check == 2) {
				int sum1 = 0, sum2 = 0;
				for (int i = 0; i < cnt; i++) {
					if (isSelected[i])
						sum1 += population[i];
					else
						sum2 += population[i];
				}
				int diff = Math.abs(sum1 - sum2);
				min = Math.min(min, diff);
			}
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1, isSelected);

		isSelected[cnt] = false;
		subset(cnt + 1, isSelected);

	}

    static void dfs(int idx, boolean[] visited, boolean[] isSelected) {
		if (idx > N)
			return;

		visited[idx] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && isSelected[i] == isSelected[idx] && region.get(idx).contains(i)) {
				dfs(i, visited, isSelected);
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		make(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				int node = Integer.parseInt(st.nextToken());
				region.get(i).add(node);
				region.get(node).add(i);
			}
		}
		
		
		
		int totalPeople = 0;
		for(int i=1;i<=N;i++) {
			totalPeople += population[i];
		}
		min = totalPeople;
        
		subset(1, new boolean[N + 1]);
		if(min == totalPeople) System.out.println(-1);
		else System.out.println(min);
	}
}