import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int l = Integer.parseInt(str[0]);
		int r = Integer.parseInt(str[1]);
		
		int[][] board = new int[l][r];
		for(int i=0; i<l; i++) {
			str = br.readLine().split("");
			for(int j=0; j<str.length; j++) {
				board[i][j] = Integer.parseInt(str[j]);
			}
		}

		int result = 1;
		for(int startL=0; startL<l; startL++) {
			for(int startR=0; startR<r; startR++) {
				for(int endR=startR+1; endR<r; endR++) {
					int size = endR-startR;
					int endL = startL+size;
					if(endL>l-1 || endR>r-1) {
						break;
					}
					if(board[startL][startR] == board[startL][endR] &&
						board[startL][startR] == board[endL][startR] &&
						board[startL][startR] == board[endL][endR]) {
						int tmp = (endL-startL+1)*(endR-startR+1);
						result = Math.max(result, tmp);
					}
				}
			}
		}
		System.out.println(result);
	}
}