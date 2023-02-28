import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int p = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		
		List<Integer> list = new ArrayList<>();
		for(String s : str) {
			list.add(Integer.parseInt(s));
		}
		Collections.sort(list);
		
		int result=0;
		for(int i=0; i<list.size(); i++) {
			result += list.get(i)*(list.size()-i);
		}
		
		System.out.println(result);
	}

}