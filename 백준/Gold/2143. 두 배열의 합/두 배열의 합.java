import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long T = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int lenA = Integer.parseInt(st.nextToken());
		
		int[] A = new int[lenA];
		int[] sumA = new int[lenA+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < lenA; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			sumA[i+1] = sumA[i]+A[i];
		}
		
		st = new StringTokenizer(br.readLine());
		int lenB = Integer.parseInt(st.nextToken());

		int[] B = new int[lenB];
		int[] sumB = new int[lenB+1];
		Map<Long, Integer>BMap = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < lenB; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			sumB[i+1] = sumB[i]+B[i];
		}
		
		for(int i = 1; i < lenB+1; i++) {
			for(int j = 0; j < i; j++) {	
				long temp = sumB[i] - sumB[j];
				if(BMap.containsKey(temp))BMap.replace(temp, BMap.get(temp)+1);
				else BMap.put(temp, 1);
			}
		}
		

		long result = 0;
		for(int i = 1; i < lenA+1; i++) {
			for(int j = 0; j < i; j++) {
				int pickA = sumA[i] - sumA[j];
				long target = T-pickA;
				if(BMap.containsKey(target)) {
					result+=BMap.get(target);
				}
			}
		}
		

		System.out.println(result);

	}

}