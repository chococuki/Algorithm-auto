import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	
    	StringBuilder tmp = new StringBuilder();
    	StringBuilder result = new StringBuilder();
    	for (int i = 1; i <= N; i++) {
			tmp.append("*");
			result.append(tmp.toString()+"\n");
		}
    	
    	System.out.println(result.toString());
    }
}