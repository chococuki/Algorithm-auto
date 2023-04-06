import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final String result = "123456780";
	static HashSet<String> hash = new HashSet<>();
	static PriorityQueue<Map> fastRoute = new PriorityQueue<>();
	
	static class Map implements Comparable<Map>{
		int[][] arr;
		int dept;
		
		public Map(int[][] arr, int dept) {
			super();
			this.arr = arr;
			this.dept = dept;
		}

		@Override
		public int compareTo(Map o) {
			return Integer.compare(this.dept, o.dept);
		}
	}
	
	private static int startMove() {
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		while (!fastRoute.isEmpty()) {
			Map now = fastRoute.poll();
			
			int index0 = find0(now.arr);
			
			for (int i = 0; i < 4; i++) {
				int row = index0/3 + dr[i];
				int col = index0%3 + dc[i];
				
				if(row>=0 && row<3 && col>=0 && col<3) {
					int tmp = now.arr[index0/3][index0%3];
					now.arr[index0/3][index0%3] = now.arr[row][col];
					now.arr[row][col] = tmp;
					String strArray = arrayToString(now.arr);
					if(strArray.equals(result)) {
						return now.dept+1;
					} else if(!hash.contains(strArray)) {
						hash.add(strArray);
						fastRoute.add(new Map(copyArray(now.arr), now.dept+1));
					}
					now.arr[row][col] = now.arr[index0/3][index0%3];
					now.arr[index0/3][index0%3] = tmp;
				}
			}
		}
		
		return -1;
	}
	
	private static int find0(int[][] arr) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(arr[i][j] == 0) return i*3+j;
			}
		}
		return -1;
	}
	
	private static int[][] copyArray(int[][] arr) {
		int[][] tmp = new int[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		
		return tmp;
	}
	
	private static String arrayToString(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(arr[i][j]);
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] arr = new int[3][3];
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		fastRoute.add(new Map(arr, 0));
		
		System.out.println(!arrayToString(arr).equals(result) ? startMove() : 0);
		
	}
}