import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] sudoku;
	static StringBuilder sb = new StringBuilder();

	private static boolean makeSudoku(int start) {
		boolean hasZero = false;
		ArrayList<Integer> numbers = new ArrayList<Integer>(); // 가능한 수 리스트
		for (int k = start / 9; k < 81; k++) {
			int i = k / 9;
			int j = k % 9;
			if (sudoku[i][j] == 0) {
				hasZero = true;
				getNumbers(i, j, numbers); // 가능한 수 찾기
				for (int n : numbers) {
					sudoku[i][j] = n;
					if (makeSudoku(k + 1)) {
						return true;
					}
				}
				sudoku[i][j] = 0;
				return false;
			}
		}
		
		//더이상 빈칸이 없으면 종료
		if (!hasZero) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]);
				}
				sb.append("\n");
			}
			return true;
		}
		return false;
	}

	private static void getNumbers(int i, int j, ArrayList<Integer> numbers) {
		numbers.clear();
		boolean[] used = new boolean[10];
		// 가로, 세로에 있는 수 제외
		for (int a = 0; a < 9; a++) {
			used[sudoku[i][a]] = true;
			used[sudoku[a][j]] = true;
		}
		// 네모칸에 있는 수 제외
		int row = (i / 3) * 3;
		int col = (j / 3) * 3;
		for (int a = row; a < row + 3; a++) {
			for (int b = col; b < col + 3; b++) {
				used[sudoku[a][b]] = true;
			}
		}
		// 가능한 수 리스트 생성
		for (int n = 1; n <= 9; n++) {
			if (!used[n]) {
				numbers.add(n);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;

		sudoku = new int[9][9];

		for (int i = 0; i < 9; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(str[j]);
			}
		}

		makeSudoku(0);

		System.out.println(sb.toString());
	}
}