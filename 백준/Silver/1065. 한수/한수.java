import java.util.Scanner;

public class Main {
	static int count=0;
	
	private static void find(int num) {
		if(num<100) count++;
		else {
			String[] str = Integer.toString(num).split("");
			int len = str.length;
			int k = Integer.parseInt(str[1]) - Integer.parseInt(str[0]);
			boolean isit = true;
			//System.out.print(num+" : "+k+" ");

			for(int i=1; i<len-1; i++) {
				//System.out.print(Integer.parseInt(str[i+1])-Integer.parseInt(str[i]));
				if(Integer.parseInt(str[i+1])-Integer.parseInt(str[i]) != k) {
					isit = false;
					break;
				}
			}

			if(isit) {
				//System.out.print("  true");
				count++;
			}
			//System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i=1; i<=N; i++) {
			find(i);
		}
		System.out.println(count);
	}
}
