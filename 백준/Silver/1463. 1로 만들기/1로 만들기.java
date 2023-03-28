import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] board = new int[n+1];

		board[1] = 0;
		for (int i = 2; i < n+1; i++)
		{
			board[i] = board[i-1]+1;
			if (i % 2 == 0 && board[i] > board[i/2]+1)
			{
				board[i] = board[i/2]+1;
			}
			if (i % 3 == 0 && board[i] > board[i/3]+1)
			{
				board[i] = board[i/3]+1;
			}
		}
		System.out.println(board[n]);
	}
}