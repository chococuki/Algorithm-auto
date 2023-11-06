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

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int count = 0;
		for (int i = 0; i < N; i++) {
			int number = arr[i];
			int start = 0;
			int end = N - 1;
			while (true) {
				if (start == i) {
					start++;
				} else if (end == i) {
					end--;
				}

				if (start >= end) {
					break;
				}

				int sum = arr[start] + arr[end];
				if (sum > number) {
					end--;
				} else if (sum < number) {
					start++;
				} else {
					count++;
					break;
				}
			}
		}

		System.out.println(count);
	}
}