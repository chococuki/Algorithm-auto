import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        
        while (true) {
        	int sum = sc.nextInt()+sc.nextInt();
        	if(sum==0) break;
			sb.append(sum+"\n");
		}
        
        System.out.println(sb.toString());
    }
}