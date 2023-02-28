import java.io.*;
import java.util.*;

public class Main {
	static int[] m = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	static boolean check(int[] m, int j) {
		for(int i=0; i<j; i++) {
			if(m[i] == m[j] && i != j) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] m = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		int count = 0;

		for(int i=0; i<10; i++) {
			m[i] = Integer.parseInt(br.readLine())%42;
		}
		
		for(int i=0; i<10; i++) {
			if(check(m, i)) count++;
		}
		
		System.out.println(count);
		
	}
}
