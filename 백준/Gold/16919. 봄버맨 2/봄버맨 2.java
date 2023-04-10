import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int R, C, N;
	static char[][] map, allBomb;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static Map<Integer, String> hindex = new LinkedHashMap<>();
	
	private static String arrToString(char[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private static char[][] copyArr(char[][] arr){
		char[][] tmp = new char[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tmp[r][c] = arr[r][c];
			}
		}
		return tmp;
	}
	
	private static void start() {
		int cycleStart = findCycle(1);
		int cycleLength = hindex.size() - cycleStart;
		
		int result = N;
		if(N > cycleLength) {
			result = ((N-cycleStart)/2) % cycleLength;
			if(cycleStart != 0 && result == 0) result = cycleLength;
		}
		
		System.out.println(hindex.get(result));
	}
	
	private static int findCycle(int cnt) {
		char[][] nextmap = copyArr(allBomb);
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] == 'O') {
					nextmap[r][c] = '.';
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(nr>=0 && nr<R && nc>=0 && nc<C) {
							nextmap[nr][nc] = '.';
						}
					}
				}
			}
		}
		
		String strArr = arrToString(nextmap);
		if(hindex.containsValue(strArr)) {
			for (int i = 0; i < hindex.size(); i++) {
				if(hindex.get(i).equals(strArr)) {
					return i;
				}
			}
		} else {
			hindex.put(cnt, strArr);
			map = nextmap;
			return findCycle(cnt+1);
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		allBomb = new char[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				allBomb[r][c] = 'O';
			}
		}
		
		hindex.put(0, arrToString(map));
		
		if(N == 1) {
			System.out.println(hindex.get(0));
		} else if(N%2 == 0) {
			StringBuilder sb = new StringBuilder();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					sb.append(allBomb[r][c]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
		} else {
			start();
		}
	}
}