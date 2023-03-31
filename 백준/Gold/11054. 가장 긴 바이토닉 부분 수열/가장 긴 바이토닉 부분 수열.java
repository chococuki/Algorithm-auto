import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int result = 0;

		// i-> 중간지점
		for (int mid = 0; mid < N; mid++) {
			int midNum = arr[mid];

			// 중간지점 앞쪽 증가하는 수열 찾기
			int frontIndex = 0;
			int[] frontArr = new int[N];
			frontArr[0] = arr[0];
			for (int f = 0; f < mid; f++) {
				if (arr[f] >= midNum)
					continue;

				if (frontArr[frontIndex] < arr[f]) {
					frontArr[++frontIndex] = arr[f];
				} else {
					for (int i = frontIndex; i >= 0; i--) {
						if (arr[f] == frontArr[i])
							break;
						else if (arr[f] > frontArr[i]) {
							frontArr[++i] = arr[f];
							break;
						} else if(i==0) {
							frontArr[i] = arr[f];
						}
					}
				}
			}

			// 중간지점 뒤쪽 감소하는 수열 찾기
			int endIndex = 0;
			int[] endArr = new int[N];
			endArr[0] = arr[N - 1];
			for (int e = N - 1; e > mid; e--) {
				if (arr[e] >= midNum)
					continue;

				if (endArr[endIndex] < arr[e]) {
					endArr[++endIndex] = arr[e];
				} else {
					for (int i = endIndex; i >= 0; i--) {
						if (arr[e] == endArr[i])
							break;
						else if (arr[e] > endArr[i]) {
							endArr[++i] = arr[e];
							break;
						} else if(i==0) {
							endArr[i] = arr[e];
						}
					}
				}

			}

			if (frontArr[frontIndex] < midNum)
				frontIndex++;
			if (endArr[endIndex] < midNum)
				endIndex++;

			result = Math.max(result, (frontIndex + endIndex + 1));
		}

		System.out.println(result);

	}
}