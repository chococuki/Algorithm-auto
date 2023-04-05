import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 값 받기
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		// 초밥 종류 배열 생성 및 초기화
		int[] rail = new int[N];
		for (int i = 0; i < N; i++) {
			rail[i] = Integer.parseInt(br.readLine()); // i번째 위치에 있는 초밥 종류 입력 받기
		}
		
		int[] susi = new int[d+1]; // 각 초밥 종류가 몇 개 들어있는지 저장하는 배열
		susi[c]++; // 쿠폰 번호의 초밥 종류 카운트
		
		int count = 1; // 초밥 종류 중복 카운트 (쿠폰 번호 초밥 포함)
		
		// 처음 k개의 접시에 대한 초밥 종류 카운트
		for (int i = 0; i < k; i++) {
			if(++susi[rail[i]] == 1) count++; // 카운트가 0에서 1로 증가한 경우에만 중복 카운트 증가
		}
		
		int result = count; // 초밥 종류 중복 카운트의 최댓값 (초기값은 k개의 접시 중복 카운트)
		
		// 회전 초밥 벨트를 한 바퀴 돌며 초밥 종류 중복 카운트의 최댓값 계산
		for (int i = k; i < (N+k); i++) {
			if(++susi[rail[i%N]] == 1) count++; // 카운트가 0에서 1로 증가한 경우에만 중복 카운트 증가
			if(--susi[rail[(i-k)%N]] == 0) count--; // k번째 전 접시의 초밥 종류 카운트를 1 감소시키고, 0이 된 경우에만 중복 카운트 감소
			result = Math.max(result, count); // 초밥 종류 중복 카운트의 최댓값 갱신
		}
		
		System.out.println(result); // 결과 출력
	}
}