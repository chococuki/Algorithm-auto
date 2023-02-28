import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String[] func = br.readLine().split("");
			
			int n = Integer.parseInt(br.readLine());
			
			String[] str = br.readLine().split(",");
			
			Deque<String> list = new LinkedList<>();
			
			str[0] = str[0].replace("[", "");
			str[str.length-1] = str[str.length-1].replace("]", "");
			
			for(int i=0; i<str.length; i++) {
				if(str[i] != "")
					list.add(str[i]);
			}
			boolean end = true;
			boolean reverse = false;
			for(String s:func) {
				if(s.equals("R")) {
					reverse = !reverse;
				} else if(s.equals("D")) {
					if(list.size()==0) {
						end = false;
						break;
					} else if (reverse){
						list.pollLast();
					} else if (!reverse) {
						list.pollFirst();
					}
				}
			}
			if(end) {
				bw.append("[");
				while(!list.isEmpty()) {
					if(reverse)	bw.append(list.pollLast());
					else bw.append(list.pollFirst());
					
					if(!list.isEmpty())
						bw.append(",");
				}
				bw.append("]\n");
			}
			else bw.write("error\n");
		}
		bw.flush();
		bw.close();
	}
}