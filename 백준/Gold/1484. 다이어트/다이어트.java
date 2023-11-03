import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long G = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		long front = 2;
		long back = 1;

		while (front <= 50000 && front >= back) {
			long between = (long)(Math.pow(front, 2) - Math.pow(back, 2));

			if (between == G) {
				sb.append(front).append("\n");
				front++;
			} else if (between > G) {
				back++;
			} else {
				front++;
			}
		}

		if (sb.toString().equals("")) {
			sb.append(-1);
		}

		System.out.println(sb);
	}
}