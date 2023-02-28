import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 10;
		long n = 20;
		String[] str;
		Boolean isCount = true;

		int N = Integer.parseInt(br.readLine());

		if (N >= 1023) {
			System.out.println("-1");
		} else if (N <= 10) {
			System.out.println(N);
		} else {
			while (true) {
				str = Long.toString(n).split("");
				isCount = true;
				for (int i = 0; i < str.length - 1; i++) {
					if (Integer.parseInt(str[i]) <= Integer.parseInt(str[i + 1])) {

						String s = "";
						if (Integer.parseInt(str[i]) == Integer.parseInt(str[i + 1])) {
							str[i] = Integer.toString(Integer.parseInt(str[i + 1]) + 1);
							str[i + 1] = "0";
							for (int j = 0; j < str.length; j++) {
								s = s.concat(str[j]);
							}
							n = Long.parseLong(s) - 1;
						}
						isCount = false;
						break;
					}
				}

				if (isCount == true) {
					cnt++;
				}

				if (cnt == N)
					break;
				else {
					n++;
				}
			}
			System.out.println(n);
		}
	}
}