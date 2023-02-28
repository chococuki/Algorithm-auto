import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> tmp = null;
		for(int i=1; i<=n; i++) {
			 String[] str = br.readLine().split(" ");
			 
			 List<Integer> list = new ArrayList<>();
			 
			 int[] tin = new int[i];
			 if(tmp != null) {
				 tin[0] = tmp.get(0);
			 
				 for(int j=1; j<i-1; j++) {
					 tin[j] = Math.max(tmp.get(j-1), tmp.get(j));
				 }
				 tin[i-1] = tmp.get(tmp.size()-1);
			 }
			 else {
				 tin[0] = 0;
			 }
		 
			 for(int j=0; j<str.length; j++) {
				 list.add(Integer.parseInt(str[j])+tin[j]);
			 }
			 tmp = list;
		}
		Collections.sort(tmp);
		System.out.println(tmp.get(tmp.size()-1));
	}
}