import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, h, k;
	public static boolean catcherRot = false;
	
	public static int[] dr = {0, 0, 1, -1};		// 좌, 우, 하, 상
	public static int[] dc = {-1, 1, 0, 0};
	
	public static int[] dcr = {-1, 0, 1, 0};	// 상, 우, 하, 좌
	public static int[] dcc = {0, 1, 0, -1};
	
	public static Node catcher;
	
	public static List<Integer>[][] map;
	public static boolean[][] tree;
	public static boolean[][] visited;
	public static Map<Integer, Node> runners;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// map 크기
		m = Integer.parseInt(st.nextToken());	// 도망자 수
		h = Integer.parseInt(st.nextToken());	// 나무 수
		k = Integer.parseInt(st.nextToken());	// 턴 수
		
		// 도망자 위치
		map = new ArrayList[n+1][n+1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		runners = new LinkedHashMap<>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int rot = Integer.parseInt(st.nextToken());
			
			runners.put(i+1, new Node(i+1, r, c, rot));
			map[r][c].add(i+1);
		}
		
		// 나무 위치
		tree = new boolean[n+1][n+1];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			tree[r][c] = true;
		}
		
		catcher = new Node(0, n/2+1, n/2+1, 0);
		
		visited = new boolean[n+1][n+1];
		visited[n/2+1][n/2+1] = true;
		
		// k턴 동안 진행
		int result = 0;
		int turn = 1;
		for(int i = 0; i < k; i++) {
			moveRunner();
			moveCatcher();
			result += catchRunner() * turn;
			turn++;
			if(runners.size() == 0) {
				break;
			}
		}
		
		System.out.println(result);
	}

	
	public static void moveRunner() {
		for(Node node : runners.values()) {
			
			// 도망자와 술래의 거리
			int d = Math.abs(node.r - catcher.r) + Math.abs(node.c - catcher.c);
			if(d > 3) {
				continue;
			}
			
			int nr = node.r + dr[node.rot];
			int nc = node.c + dc[node.rot];
			
			// map 밖이면 방향 전환후 이동
			if(nr <= 0 || nr > n || nc <= 0 || nc > n) {
				node.rot = (node.rot % 2 == 1) ? (node.rot - 1) : (node.rot + 1);
				nr = node.r + dr[node.rot];
				nc = node.c + dc[node.rot];
			}
			
			// 이동 하려는 칸에 술래가 있는 경우
			if(nr == catcher.r && nc == catcher.c) {
				continue;
			}
			
			if(nr > 0 && nr <= n && nc > 0 && nc <= n) {
				map[node.r][node.c].remove(map[node.r][node.c].indexOf(node.i));
				map[nr][nc].add(node.i);
				
				node.r = nr;
				node.c = nc;
			}
			
		}
	}
	
	public static void moveCatcher() {
		int nr = catcher.r + dcr[catcher.rot];
		int nc = catcher.c + dcc[catcher.rot];
		
		
		// 끝or 처음에 도달하면 반대로
		if((nr == 1 && nc == 1) || (nr == n/2+1 && nc == n/2+1)) {
			catcher.rot = (catcher.rot + 2) % 4;
			catcherRot = !catcherRot;
			catcher.r = nr;
			catcher.c = nc;
			return;
		}
		
		if(!catcherRot) {
			// 끝이 아니면 시계 방향으로
			visited[nr][nc] = true;
			if(!visited[nr + dcr[(catcher.rot + 1)%4]][nc + dcc[(catcher.rot + 1)%4]]) {
				catcher.rot = (catcher.rot + 1) % 4;
			}
		} else {
			visited[nr][nc] = false;
			int nnr = nr + dcr[catcher.rot];
			int nnc = nc + dcc[catcher.rot];
			// 범위 안이면
			if(nnr >0 && nnr <= n && nnc > 0 && nnc <= n) {
				if(!visited[nnr][nnc]) {
					catcher.rot = catcher.rot == 0 ? 3 : catcher.rot - 1;
				}
			} else {
				catcher.rot = catcher.rot == 0 ? 3 : catcher.rot - 1;
			}
		}
		
		catcher.r = nr;
		catcher.c = nc;
	}
	
	public static int catchRunner() {
		int count = 0;
		
		// 시야 범위 3
		for(int i = 0; i < 3; i++) {
			int nr = catcher.r + dcr[catcher.rot] * i;
			int nc = catcher.c + dcc[catcher.rot] * i;
			
			// map 밖이면 종료
			if(nr <= 0 || nr > n || nc <= 0 || nc > n) {
				break;
			}
			
			// 나무 위치면 넘김
			if(tree[nr][nc]) {
				continue;
			}
			
			
			// 해당 위치에 도망자가 있으면
			if(map[nr][nc].size() > 0) {
				count += map[nr][nc].size();
				for(int index : map[nr][nc]) {
					runners.remove(index);
				}
				map[nr][nc] = new ArrayList<>();
			}
		}
		
		return count;
	}
	
	public static class Node {
		int i;
		int r;
		int c;
		int rot;
		
		public Node(int i, int r, int c, int rot) {
			this.i = i;
			this.r = r;
			this.c = c;
			this.rot = rot;
		}
		
		public String toString() {
			if(rot == 0) {
				return i+" "+r+" "+c+" 상";
			} else if(rot == 1) {
				return i+" "+r+" "+c+" 우";
			} else if(rot == 2) {
				return i+" "+r+" "+c+" 하";
			} else {
				return i+" "+r+" "+c+" 좌";
			}
		}
	}
}
