import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		int maxStudent = Integer.parseInt(str[0]);
		int inputStudent = Integer.parseInt(str[1]);
		
		LinkedHashSet<String> load = new LinkedHashSet<>();
		String tmp = "";
		for(int i=0; i<inputStudent; i++) {
			tmp = br.readLine();
			if(load.contains(tmp)) {
				load.remove(tmp);
			}
			load.add(tmp);
		}
		
		Object[] obj = load.toArray();
		if(maxStudent > obj.length) {
			maxStudent = obj.length;
		}
		
		for(int i=0; i<maxStudent; i++) {
			bw.write(obj[i]+"\n");
		}
		bw.flush();
		bw.close();
	}
}