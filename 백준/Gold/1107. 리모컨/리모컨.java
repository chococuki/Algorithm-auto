import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int target = Integer.parseInt(br.readLine());

		int blockCnt = Integer.parseInt(br.readLine());

		int count = 0;

		boolean[] broken = new boolean[10];

		// 고장난 버튼이 없을 경우
		if (blockCnt == 0) {
			count = Integer.toString(target).length();
			count = Math.min(count, Math.abs(target - 100));
		} else {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 버튼이 전부 고장 났을떄
			if (blockCnt == 10) {
				count = Math.abs(target - 100);
			} else {
				for (int i = 0; i < blockCnt; i++) {
					broken[Integer.parseInt(st.nextToken())] = true;
				}

				// +- 1로만 이동 했을 경우
				count = Math.abs(target - 100);

				// 채널 이동 후 +-로 이동
				for (int i = 0; i < 1000000; i++) {
					int pushCount = checkNumber(broken, i);

					if (pushCount != 0) {
						count = Math.min(count, Math.abs(i - target) + pushCount);
					}
				}
			}

		}

		System.out.println(count);
	}

	// 해당 채널 이동 가능 하면 버튼 누른 횟수, 이동 불가능 하면 0
	public static int checkNumber(boolean[] broken, int number) {
		int count = 0;

		if (number == 0) {
			if (broken[0]) {
				return 0;
			} else {
				return 1;
			}
		}

		while (number > 0) {
			if (broken[number % 10]) {
				return 0;
			} else {
				count++;
				number /= 10;
			}
		}

		return count;
	}
}