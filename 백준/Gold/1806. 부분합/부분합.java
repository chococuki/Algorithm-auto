import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] numbers = new int[N];
		int[] partNum = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		partNum[0] = numbers[0];

		for (int i = 0; i < N; i++) {
			partNum[i+1] = numbers[i] + partNum[i];
		}

		int start = 0;
		int end = 0;
		int result = Integer.MAX_VALUE;

		while (end < N+1) {
//			System.out.println(start+" "+end);
			int temp = partNum[end] - partNum[start];

			// 만약에 S보다 작을 경우엔 end를 1칸 밀어야 하고
			if (temp < S) {
				end = end + 1;
			}

			// S보다 클 경우엔 start를 1칸 밀면 된다 그리고 최소 길이 업데이트
			else {
				result = Math.min(result, (end - start));
				if(result == 0) {
					result = 1;
					break;
				}
				start = start + 1;
			}

		}
		
		if(result!=Integer.MAX_VALUE)System.out.println(result);
		else System.out.println(0);
	}

}