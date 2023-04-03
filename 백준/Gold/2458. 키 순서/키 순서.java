import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 각 학생이 키 비교 결과를 담을 배열
		// tall[a][b] = 1 -> 학생 a가 학생 b보다 키가 작음
		// tall[a][b] = INF -> 정보 없음
		int[][] tall = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(tall[i], Integer.MAX_VALUE);
		}
		
		// 입력 받은 각 학생 키 비교 결과를 tall 배열에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			tall[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		// 각 학생의 키 비교 정보를 플로이드-와샬 알고리즘으로 최소 비용으로 업데이트
		for (int stop = 1; stop <= N; stop++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					if(tall[start][stop] != Integer.MAX_VALUE
							&& tall[stop][end] != Integer.MAX_VALUE) {
						tall[start][end] = Math.min(tall[start][end], tall[start][stop] + tall[stop][end]);
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 1; i <= N; i++) {
			int count = 0;
			// 학생 i와 비교 가능한 학생 수 count 세기
			for (int j = 1; j <= N; j++) {
				if(i != j) {
					if(tall[i][j] != Integer.MAX_VALUE) count++;  // i보다 j가 키가 큼
					if(tall[j][i] != Integer.MAX_VALUE) count++;  // j보다 i가 키가 큼
				}
			}
			if(count == N-1) result++;  // i와 비교 가능한 학생 수가 N-1이면 result 증가
		}
		
		System.out.println(result);  // 결과 출력
	}
}