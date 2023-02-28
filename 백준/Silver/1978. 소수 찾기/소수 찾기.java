import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String[] st = br.readLine().split(" ");
		
		List<Integer> numbers = new ArrayList<>();
		for(int i=0; i<st.length; i++) {
			numbers.add(Integer.parseInt(st[i]));
		}
		Collections.sort(numbers);
		
		List<Integer> prime = new ArrayList<>();
		prime.add(2);
		for(int i=3; i<=numbers.get(numbers.size()-1); i++) {
			boolean isPrime = true;
			for(int p : prime) {
				if(i%p == 0) {
					isPrime = false;
				}
			}
			if(isPrime) prime.add(i);
		}
		
		int count = 0;
		for(int i : numbers) {
			if(prime.contains(i)) {
				count++;
			}
		}
		System.out.println(count);
	}
}
