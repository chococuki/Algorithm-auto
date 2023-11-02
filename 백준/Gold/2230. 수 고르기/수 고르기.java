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

		Long[] numbers = new Long[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(numbers);

		int front = 0;
		int back = 0;
		long min = Long.MAX_VALUE;

		while (front < N && front >= back) {
			long between = numbers[front] - numbers[back];
			if (between > M) {
				min = Math.min(min, between);
				back++;
			} else if (between < M) {
				front++;
			} else {
				min = M;
				break;
			}
		}

		System.out.println(min);
	}
}