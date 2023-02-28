import java.io.*;

public class Main {
	static int board[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int number = Integer.parseInt(br.readLine());
		
		board = new int[number+1];

		board[1] = 0;
		for (int i = 2; i < number+1; i++)
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
		System.out.println(board[number]);
		
		
	}
}
