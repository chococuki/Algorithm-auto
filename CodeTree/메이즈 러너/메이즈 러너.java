import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K;
	
	public static int[] dr = {1, -1, 0, 0};
	public static int[] dc = {0, 0, 1, -1};
	
	public static List<Integer>[][] map;
	
	public static Node exit;
	public static Map<Integer, Node> people;
	
	public static int result = 0;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 미로 저장
		map = new List[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = new ArrayList<>();
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					map[i][j].add(num);
				}
			}
		}
		
		// 참가자
		int r, c;
		people = new LinkedHashMap<>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map[r][c].add(-i);
			people.put(-i, new Node(-i, r, c));
		}
		
		// 출구
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		exit = new Node(0, r, c);
		map[r][c].add(0);
		
//		printMap();
//		System.out.println("=========");
		for (int i = 0; i < K; i++) {
			movePerson();
			// 남은 참가자가 없으면 종료
			if(people.size() == 0) {
				break;
			}
//			printMap();
			rotateMap();
//			printMap();
//			System.out.println("\n===========================\n");
		}
		
		System.out.println(result);
		System.out.println(exit.r + " " + exit.c);
	}
	
	public static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j].toString());
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void movePerson() {
		int nc, nr, ll, nl;
		List<Integer> del = new ArrayList<>();
		for(Node node : people.values()) {
			// 상하좌우 순으로 거리, 벽 확인 후 이동
			for(int i = 0; i < 4; i++) {
				nr = node.r + dr[i];
				nc = node.c + dc[i];
				
				// 가려는 곳이 미로 밖이면
				if(nr <= 0 || nr > N || nc <= 0 || nc > N) {
					continue;
				}
				
				// 가려는 곳이 벽이면
				List<Integer> to = map[nr][nc];
				boolean cango = true;
				for(int t : to) {
					if(t > 0) {
						cango = false;
						break;
					}
				}
				
				if(cango) {
					ll = Math.abs(node.r - exit.r) + Math.abs(node.c - exit.c);
					nl = Math.abs(nr - exit.r) + Math.abs(nc - exit.c);
					
					// 거리가 가까워 지면
					if(nl < ll) {
						// 이동 거리 추가
						result++;
						map[node.r][node.c].remove(map[node.r][node.c].indexOf(node.i));
						
						// 이동한 곳이 출구이면 탈출
						if(nr == exit.r && nc == exit.c) {
							del.add(node.i);
							break;
						// 출구가 아니면 이동
						} else {
							map[nr][nc].add(node.i);
							node.r = nr;
							node.c = nc;
							break;
						}
					}
				}
			}
		}
		
		for(int i : del) {
			people.remove(i);
		}
	}
	
	public static void rotateMap() {
		int length = Integer.MAX_VALUE;	// 정사각형 길이
		int index = 0;
		Node start = new Node(0, 0, 0);
		Node end = new Node(0, 0, 0);
		
		int tmp;
		for(Node node : people.values()) {
			tmp = Math.max(Math.abs(node.r - exit.r), Math.abs(node.c - exit.c));
			
			// 이전 거리가 더 작을떄
			if(length < tmp) {
				continue;
			// 이번 거리가 더 작을때
			} else if(length > tmp) {
				length = tmp;
				index = node.i;
				getStartEnd(start, end, node, tmp);
			// 이전 최소 거리가 같을때
			} else {
				Node lastNode = people.get(index);
				Node nstart = new Node(0, 0, 0);
				Node nend = new Node(0, 0, 0);
				getStartEnd(nstart, nend, node, length);
				// r이 같으면 c가 더 작은것으로
				if(nstart.r == start.r) {
//					System.out.println(node.c+" "+lastNode.c);
					if(nstart.c < start.c) {
						index = node.i;
						start = nstart;
						end = nend;
					}
				// r이 더 작은 것으로
				} else {
					if(nstart.r < start.r) {
						index = node.i;
						start = nstart;
						end = nend;
					}
				}
			}
		}
		
		// 회전, 내구도 -1
		Queue<List<Integer>> tmpList = new LinkedList<>();
		for(int i = start.r; i <= end.r; i++) {
			for(int j = start.c; j <= end.c; j++) {
				List<Integer> tl = new ArrayList<>(map[i][j]);
				for(int n = 0; n < tl.size(); n++) {
					if(tl.get(n) > 0) {
						if(tl.get(n) != 1) {
							tl.add(tl.get(n)-1);
						}
						tl.remove(n);
						break;
					}
				}
				tmpList.add(tl);
			}
		}
		
		for(int j = end.c; j >= start.c; j--) {
			for(int i = start.r; i <= end.r; i++) {
				map[i][j] = tmpList.poll();
				for(int t : map[i][j]) {
					if(t == 0) {
						exit.r = i;
						exit.c = j;
					} else if(t < 0) {
						people.get(t).r = i;
						people.get(t).c = j;
					}
				}
			}
		}
	}
	
	public static void getStartEnd(Node start, Node end, Node node, int length) {
		end.r = Math.max(exit.r, node.r);
		end.c = Math.max(exit.c, node.c);
		
		if(end.r - length > 0) {
			start.r = end.r - length;
		} else {
			start.r = 1;
			end.r = length + 1;
		}
		
		if(end.c - length > 0) {
			start.c = end.c - length;
		} else {
			start.c = 1;
			end.c = length + 1;
		}
	}
	
	public static class Node {
		int i;
		int r;
		int c;
		
		public Node(int i, int r, int c) {
			this.i = i;
			this.r = r;
			this.c = c;
		}
		
		public String toString() {
			return "Node: "+i;
		}
	}

}
