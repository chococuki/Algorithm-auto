import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list;
	static int[] arr = new int[6];
	static StringBuilder sb = new StringBuilder();
	
	private static void find(int cnt, int start) {
		if(cnt==6) {
			for(int i=0; i<6; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			arr[cnt] = list.get(i);
			find(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			if(count == 0) break;
			
			list = new ArrayList<>();
			
			for(int i=0; i<count; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list);
			
			find(0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}