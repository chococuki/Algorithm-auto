import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int P,S;
	static int resultcnt;
	static String[] dna;
	static int[] tmp = null;
	static int[] acgt = new int[4];
	
	private static void find(int cnt, int start) {
		if((start+cnt)>P) return;
		
		if(tmp==null) {
			tmp = new int[4];
			
			for(int i=start; i<(start+cnt); i++) {
//				System.out.print(dna[i]);
				switch(dna[i]) {
					case "A":
						tmp[0]++;
						break;
					case "C":
						tmp[1]++;
						break;
					case "G":
						tmp[2]++;
						break;
					case "T":
						tmp[3]++;
						break;
				}
			}
//			System.out.println();
			
		} else {
//			System.out.println("-"+dna[start-1]);
			switch(dna[start-1]) {
			case "A":
				tmp[0]--;
				break;
			case "C":
				tmp[1]--;
				break;
			case "G":
				tmp[2]--;
				break;
			case "T":
				tmp[3]--;
				break;
			}
			
//			System.out.println("+"+dna[start+cnt-1]);
			switch(dna[start+cnt-1]) {
				case "A":
					tmp[0]++;
					break;
				case "C":
					tmp[1]++;
					break;
				case "G":
					tmp[2]++;
					break;
				case "T":
					tmp[3]++;
					break;
			}
//			System.out.println();
		}
		
		if(tmp[0]>=acgt[0] && tmp[1]>=acgt[1] && tmp[2]>=acgt[2] && tmp[3]>=acgt[3]) {
			resultcnt++;
//			System.out.print("   true");
		}
//		System.out.println();
		
		find(cnt, start+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		str = br.readLine().split(" ");
		P = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		
		dna = br.readLine().split("");
		
		str = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			acgt[i] = Integer.parseInt(str[i]);
		}
		
		find(S, 0);
		
		System.out.println(resultcnt);
	}
}
