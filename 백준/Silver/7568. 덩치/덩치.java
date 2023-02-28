import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] kg = new int[N];
		int[] tall = new int[N];
		int[] score = new int[N];
		
		String[] str;
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			kg[i] = Integer.parseInt(str[0]);
			tall[i] = Integer.parseInt(str[1]);
			score[i] = 1;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tall[i] < tall[j] && kg[i] < kg[j])
					score[i] += 1;
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.print(score[i]+" ");
		}
	}
}