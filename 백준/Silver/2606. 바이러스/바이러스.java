import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] list;
	static boolean[] virus;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int computerN = Integer.parseInt(br.readLine());
		int lineN = Integer.parseInt(br.readLine());
		
		list = new ArrayList[computerN+1];
		virus = new boolean[computerN+1];
		virus[1] = true;
		
		for(int i=1; i<=computerN; i++) {
			list[i] = new ArrayList<>();
		}
		
		String[] str;
		for(int i=1; i<=lineN; i++) {
			str = br.readLine().split(" ");
			list[Integer.parseInt(str[0])].add(Integer.parseInt(str[1]));
			list[Integer.parseInt(str[1])].add(Integer.parseInt(str[0]));
		}
		
		boolean isChange = true;
		while(isChange) {
			isChange = false;
			
			for(int i=1; i<=computerN; i++) {
				if (virus[i]) {
					for(int j:list[i]) {
						if(!virus[j]) isChange = true;
						virus[j] = true;
					}
				}
			}
		}
		
		int count=0;
		for(int i=2; i<=computerN; i++) {
			if(virus[i]) count++;
		}
		
		System.out.println(count);
		
	}
}