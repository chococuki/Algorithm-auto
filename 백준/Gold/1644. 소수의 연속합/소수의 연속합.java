import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> prime = new ArrayList<>();
		int[] primeCheck = new int[n+1];
		
		primeCheck[0] = primeCheck[1] = 1;
		for(int i=2; i<=n; i++) {
			if(primeCheck[i] != 1) {
				prime.add(i);
				
				for(int j=(i+i); j<=n; j+=i) {
					if(primeCheck[j] != 1) {
						primeCheck[j] = 1;
					}
				}
			}
		}
		
		int count=0;
		for(int i=0; i<prime.size(); i++) {
			int sum = 0;
			for(int j=i; j<prime.size(); j++) {
				sum += prime.get(j);
				if(sum > n) break;
				else if(sum == n) {
					count++;
					break;
				}
			}
		}
		System.out.println(count);
	}
}