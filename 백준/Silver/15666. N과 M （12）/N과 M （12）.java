import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static int N, M;
	static int[] numbers;
	static StringBuilder sb;
	static List<Integer> list = new ArrayList<>();
	static Set<String> set = new LinkedHashSet<>();
	
	private static void find(int cnt, int start) {
		if(cnt == M) {
			sb = new StringBuilder();
			for(int i:numbers)
				sb.append(i+" ");
			set.add(sb.toString());
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			numbers[cnt] = list.get(i);
			find(cnt+1, i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		numbers = new int[M];
		
		str = br.readLine().split(" ");
		for(String s:str) {
			list.add(Integer.parseInt(s));
		}
		Collections.sort(list);
		
		find(0, 0);
		
		for(String i:set) {
			System.out.println(i);
		}
	}
}
