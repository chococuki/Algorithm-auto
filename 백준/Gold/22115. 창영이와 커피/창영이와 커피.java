import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] coffees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			coffees[i] = Integer.parseInt(st.nextToken());
		}

		int[] cafein = new int[K + 1];

		Arrays.fill(cafein, Integer.MAX_VALUE);
		cafein[0] = 0;

		for (int coffee : coffees) {
			for (int i = K; i >= coffee; i--) {
				if (cafein[i - coffee] != Integer.MAX_VALUE) {
					cafein[i] = Math.min(cafein[i], cafein[i - coffee] + 1);
				}
            }
		}

		System.out.println(cafein[K] == Integer.MAX_VALUE ? -1 : cafein[K]);
	}
}