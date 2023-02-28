import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, M;
	static int[] numbers;
	static List<Integer> list = new ArrayList<>();
	
	private static void find(int cnt, int start) {
		if(cnt == M) {
			for(int i:numbers)
				System.out.print(i+ " ");
			System.out.println();
			
			return;
		}
		
		for(int i=start; i<N; i++) {
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
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(str[i]));
		}
		Collections.sort(list);
		
		find(0, 0);
	}
}
