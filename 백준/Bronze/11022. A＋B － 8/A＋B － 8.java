import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int n = sc.nextInt();
        
        int a, b;
        for (int i = 1; i <= n; i++) {
        	
        	a = sc.nextInt();
        	b = sc.nextInt();
			
        	sb.append("Case #"+i+": "+a+" + "+b+" = "+(a+b)+"\n");
		}
        
        System.out.println(sb.toString());
    }
}