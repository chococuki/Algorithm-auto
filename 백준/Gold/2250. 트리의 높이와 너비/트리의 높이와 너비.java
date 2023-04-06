import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, index=1, dept = 1;
	static int[][] tree, table;
	
	private static int findRootDept() {
		int[] count = new int[N+1];
		int root = 0;
		for (int i = 1; i <= N; i++) {
			if(tree[i][0] != -1) {
				count[tree[i][0]]++;
			}
			if(tree[i][1] != -1) {
				count[tree[i][1]]++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) root = i;
		}
		
		findDept(root, 1);
		
		table = new int[dept+1][N+1];
		
		return root;
	}
	
	private static void findDept(int n, int dp) {
		dept = Math.max(dept, dp);
		if(tree[n][0] != -1) {
			findDept(tree[n][0], dp+1);
		}
		if(tree[n][1] != -1) {
			findDept(tree[n][1], dp+1);
		}
	}
	
	private static void makeBoard(int n, int dept) {
		if(tree[n][0] != -1) {
			makeBoard(tree[n][0], dept+1);
		}
		
		table[dept][index] = n;
		index++;
		
		if(tree[n][1] != -1) {
			makeBoard(tree[n][1], dept+1);
		}
	}
	
	private static String findMaxLength() {
		int level = 0;
		int length = 0;
		
		for (int i = 1; i < table.length; i++) {
			int start = 0, end = 0;
			for (int j = 1; j < table[0].length; j++) {
				if(table[i][j] != 0) {
					if(start == 0) {
						start = j;
						end = j;
					} else {
						end = j;
					}
				}
			}
			if(end - start + 1 > length) {
				length = end - start + 1;
				level = i;
			}
		}
		
		return level + " " + length;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		tree = new int[N+1][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			tree[num][0] = left;
			tree[num][1] = right;
		}
		
		makeBoard(findRootDept(), 1);
		
		System.out.println(findMaxLength());
	}
}