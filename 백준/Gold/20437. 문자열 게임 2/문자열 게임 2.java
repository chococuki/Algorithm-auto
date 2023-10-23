import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int minLength = Integer.MAX_VALUE;
			int maxLength = 0;

			char[] charArray = br.readLine().toCharArray();
			int W = Integer.parseInt(br.readLine());

			if (W == 1) {
				System.out.println(1 + " " + 1);
				continue;
			}

			List<Integer>[] alp = new ArrayList['z' - 'a' + 1];

			for (int i = 0; i < alp.length; i++) {
				alp[i] = new ArrayList<>();
			}

			for (int i = 0; i < charArray.length; i++) {
				alp[charArray[i] - 'a'].add(i);
			}

			int count = -1;
			for (List<Integer> indexs : alp) {
				if (indexs.size() < W) {
					continue;
				}

				for (int i = 0; i < indexs.size() - W + 1; i++) {
					int tmplen = indexs.get(i + W - 1) - indexs.get(i);

					minLength = Math.min(minLength, tmplen);
					maxLength = Math.max(maxLength, tmplen);
				}
			}

			if (maxLength == 0) {
				System.out.println(-1);
			} else {
				System.out.println((minLength + 1) + " " + (maxLength + 1));
			}
		}
	}
}