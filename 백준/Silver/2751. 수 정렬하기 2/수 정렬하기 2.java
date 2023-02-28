import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt((String) br.readLine());
		
		List<Integer> arr = new ArrayList<>();
		for(int i=0; i<T; i++) {
			arr.add(Integer.parseInt((String) br.readLine()));
		}
		Collections.sort(arr);
		
		for(int i=0; i<arr.size(); i++) {
			bw.write(arr.get(i)+"\n");
		}
		bw.flush();
		bw.close();
	}
}