import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		
		int T = Integer.parseInt((String) br.readLine());
		
		String[] tmpA = br.readLine().split(" ");
		String[] tmpB = br.readLine().split(" ");
		
		for(int i=0; i<T; i++) {
			a.add(Integer.parseInt(tmpA[i]));
			b.add(Integer.parseInt(tmpB[i]));
		}
		
		Collections.sort(a);
		Collections.sort(b, Collections.reverseOrder());
		
		int result = 0;
		for(int i=0; i<T; i++) {
			result += a.get(i)*b.get(i);
		}
		
		System.out.println(result);
		
		
		
	}
}