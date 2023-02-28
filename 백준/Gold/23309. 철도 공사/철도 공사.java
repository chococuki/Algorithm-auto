import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static class Node {
		int[] front;
		int[] back;
		
		public Node() {
			super();
			this.front = new int[1000001];
			this.back = new int[1000001];
		}

		void add(int target, int node) {
			if(target==-1) {
				front[node] = back[node] = node;
				return;
			}
			front[node] = target;
			back[node] = back[target];
			front[back[target]] = node;
			back[target] = node;
		}
		
		public void delete(int target) {
			front[back[target]] = front[target];
			back[front[target]] = back[target];
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		Node node = new Node();
		
		int target = -1;
		while(st.hasMoreTokens()) {
			int number = Integer.parseInt(st.nextToken());
			node.add(target, number);
			target = number;
		}
		
//		for(int j=1; j<=10; j++) {
//			System.out.print(node.front[j]+" ");
//		}
//		System.out.println();
//		for(int j=1; j<=10; j++) {
//			System.out.print(node.back[j]+" ");
//		}
//		System.out.println();
//		
//		System.out.println("-----");
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int targetN = Integer.parseInt(st.nextToken()), newNumber = -1;
			if(st.hasMoreTokens()) newNumber = Integer.parseInt(st.nextToken());
			switch (cmd){
			case "BN":
				bw.write(Integer.toString(node.back[targetN]));
				node.add(targetN, newNumber);
				break;
			case "BP":
				bw.write(Integer.toString(node.front[targetN]));
				node.add(node.front[targetN], newNumber);
				break;
			case "CN":
				bw.write(Integer.toString(node.back[targetN]));
				node.delete(node.back[targetN]);
				break;
			case "CP":
				bw.write(Integer.toString(node.front[targetN]));
				node.delete(node.front[targetN]);
				break;
			}
			bw.write("\n");
//			for(int j=1; j<=11; j++) {
//				System.out.print(node.front[j]+" ");
//			}
//			System.out.println();
//			for(int j=1; j<=11; j++) {
//				System.out.print(node.back[j]+" ");
//			}
//			System.out.println();
//			
//			System.out.println("-----");
		}
		
		bw.flush();
		bw.close();
	}
}
