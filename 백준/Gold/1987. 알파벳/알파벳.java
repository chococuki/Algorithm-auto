import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int R, C, result;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Stack<Character> stack = new Stack<>();
	static Map<Character, Integer> map = new LinkedHashMap<>();
	
	
	private static void find(int x, int y, int cnt) {
		boolean isvisit = false;
		int new_x, new_y;
		for(int i=0; i<4; i++) {
			new_x = x+dx[i];
			new_y = y+dy[i];
			
			if(new_x>0 && new_x<=R && new_y>0 && new_y<=C && !visited[new_x][new_y]) {
				if(map.get(arr[new_x][new_y])==null) {
					visited[new_x][new_y] = true;
					isvisit = true;
					map.put(arr[new_x][new_y], 1);
					find(new_x, new_y, cnt+1);
					map.remove(arr[new_x][new_y]);
					visited[new_x][new_y] = false;
				}
			}
		}
		if(!isvisit) result = Math.max(result, cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R+1][C+1];
		visited = new boolean[R+1][C+1];
		
		String str;
		for(int i=1; i<=R; i++) {
			str = br.readLine();
			for(int j=1; j<=C; j++) {
				arr[i][j] = str.charAt(j-1);
			}
		}
		
		visited[1][1] = true;
		map.put(arr[1][1], 1);
		find(1, 1, 1);
		
		System.out.println(result);
	}
}