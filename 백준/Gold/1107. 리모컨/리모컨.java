import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int target = Integer.parseInt(br.readLine());

		int blockCnt = Integer.parseInt(br.readLine());

		boolean[] broken = new boolean[10];

		// +- 1로만 이동 했을 경우
		int count = Math.abs(target - 100);

		// 고장난 버튼이 없을 경우
		if (blockCnt == 0) {
			count = Math.min(count, Integer.toString(target).length());
		} else {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 버튼이 전부 고장 나지 않았을 떄
			if (blockCnt != 10) {
				// 고장난 버튼
				for (int i = 0; i < blockCnt; i++) {
					broken[Integer.parseInt(st.nextToken())] = true;
				}

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
			return broken[0] ? 0 : 1;
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