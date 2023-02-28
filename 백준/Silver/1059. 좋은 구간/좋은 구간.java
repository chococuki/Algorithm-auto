import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt((String) br.readLine());
		
		String[] tmp = br.readLine().split(" ");
		
		List<Integer> li = new ArrayList<Integer>();
		for(int t=0; t<T; t++) {
			li.add(Integer.parseInt(tmp[t]));
		}
		Collections.sort(li);

		int num = Integer.parseInt((String) br.readLine());
		
		int min = 0;
		int max = li.get(T-1);
		for(int i=0; i<T; i++) {
			if(num == li.get(i)) {
				min = -1;
				break;
			}
			if((i+1)<T) {
				if(num>li.get(i) && num<li.get(i+1)) {
					min = li.get(i);
					max = li.get(i+1);
					break;
				}
			} else {
				min = li.get(i);
				break;
			}
			if((i-1)<0) {
				if(num < li.get(i)) {
					max = li.get(i);
					break;
				}
			}

		}
		
		int result = 0;
		if (min == -1) {
			result = 0;
		} else if((num-min) == 1 || min == num){
			result = max-num-1;
		} else if((max-num) == 1 || max == num) {
			result = num-min-1;
		} else {
			result += (num-min-1);
			result += (max-num-1);
			result += (num-min-1)*(max-num-1);
		}
		
		System.out.println(result);
	}
}
