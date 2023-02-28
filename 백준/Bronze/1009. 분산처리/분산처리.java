import java.io.*;

public class Main {
	static int[] findRule(int a) {
		int[] rule = new int[4];
		if (a == 1) {
			int[] c = {1, 1, 1, 1};
			return c;
		} else if ((a%10) == 0) {
			int[] c = {10, 10, 10, 10};
			return c;
		}
		
		int tmp = 1;
		int i = 0;
		while(i<4) {
			tmp = tmp*a;
			String stmp = Integer.toString(tmp);
			String[] t = stmp.split("");

			rule[i] = Integer.parseInt(t[t.length-1]);
			i++;
		}
		return rule;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt((String) br.readLine());
		
		for(int i=0; i<T; i++) {

			String[] tmp = br.readLine().split(" ");
			
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			
			int[] rule = findRule(a);
			
			int k = b%4-1;
			
			if (k == -1) {
				k = 3;
			}
			
			System.out.println(rule[k]);
		}
		
	}
}
