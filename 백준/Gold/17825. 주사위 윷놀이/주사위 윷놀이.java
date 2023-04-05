import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 결과 값 저장 변수
	static int result;
	// 각 말의 위치 정보
	static int[][] route = new int[10][2];

	// 한 판을 시작하는 메서드
	private static void start(int[][] map, int[] dice, int[] horse, int dept, int score) {
		// 주사위를 다 사용한 경우, 최댓값 갱신 후 종료
		if(dept == 10) {
			result = Math.max(result, score);
			return;
		}

		// 현재 말에 대한 모든 경우를 시도
		a:
		for (int h = 0; h < 4; h++) {
			int now = horse[h];
			// 말이 도착지점이 아닌 경우에만 움직임
			if(now != 32) {
				horse[h] = move(horse[h], dice[dept], map);
				// 움직인 후, 다른 말과 겹치는 경우 이동 취소
				for (int i = 0; i < 4; i++) {
					if(i != h && horse[h] != 32 && horse[i] == horse[h]) {
						horse[h] = now;
						continue a;
					}
				}
				// 다음 판을 진행
				start(map, dice, horse, dept+1, score+map[horse[h]][1]);
				// 원래 위치로 복구
				horse[h] = now;
			}
		}
	}

	// 말을 이동시키는 메서드
	private static int move(int now, int cnt, int[][] map) {
		// 이동 중 가운데 파란색 칸을 만나는 경우, 해당 위치로 이동
		if(now == 5) {
			now = 21;
			cnt--;
		} else if(now == 10) {
			now = 25;
			cnt--;
		} else if(now == 15) {
			now = 27;
			cnt--;
		}

		// 주어진 수(cnt)만큼 이동
		for (int i = 0; i < cnt; i++) {
			// 도착지점에 도달한 경우 이동 종료
			if(now == 32) break;
			// 해당 위치의 다음 위치로 이동
			now = map[now][0];
		}
		return now;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 값 초기화
		int[] dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		int[][] map = setMap();
		int[] horse = new int[4];

		// 한 판 시작
		start(map, dice, horse, 0, 0);

		System.out.println(result);
	}

	// 말판을 초기화하는 메서드
	private static int[][] setMap() {
		int[][] map = new int[33][2];
		
		for (int i = 0; i < 20; i++) {
			map[i][0] = i + 1;
			map[i][1] = i * 2;
		}
		
		for (int i = 1; i <= 3; i++) {
			map[20+i][0] = 21 + i;
			map[20+i][1] = 10 + i * 3;
		}
		
		map[24][0] = 30;
		map[24][1] = 25;
		
		map[25][0] = 26;
		map[25][1] = 22;
		
		map[26][0] = 24;
		map[26][1] = 24;
		
		map[27][0] = 28;
		map[27][1] = 28;
		
		map[28][0] = 29;
		map[28][1] = 27;
		
		map[29][0] = 24;
		map[29][1] = 26;
		
		map[24][0] = 30;
		map[24][1] = 25;
		
		map[30][0] = 31;
		map[30][1] = 30;
		
		map[31][0] = 20;
		map[31][1] = 35;
		
		map[20][0] = 32;
		map[20][1] = 40;
		
		map[32][0] = 32;
		
		return map;
	}
}