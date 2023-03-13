import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static int[] gates;
	
	//배열에 본인 게이트 저장
	private static void make(int G) {
		gates = new int[G+1];
		for (int i = 1; i <= G; i++) {
			gates[i] = i;
		}
	}
	
	//루트 게이트 찾기
	private static int find(int n) {
		if(gates[n] == n) {
			return n;
		} else {
			return gates[n] = find(gates[n]);
		}
	}
	
//	private static void union(int a, int b) {
//		int ap = find(a);
//		int bp = find(b);
//		
//		if(ap != bp) {
//			if(ap<bp) gates[ap] = bp;
//			else gates[bp] = ap;
//		}
//	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		int G = sc.nextInt();
		make(G);
		
		int P = sc.nextInt();
		
		int count = 0;
		for (int i = 0; i < P; i++) {
			int gate = sc.nextInt();
			int go = find(gate);
			if(go != 0) {
				gates[go] = go-1;
				count++;
			} else {
				break;
			}
		}
		
		System.out.println(count);
	}
}