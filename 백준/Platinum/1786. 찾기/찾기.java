import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()]; // 점프를 위해 연속된 갯수 저장

		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}

		return pi;
	}

	private static void kmp(String str, String pattern) {
		int[] pi = getPi(pattern);

		int count = 0; // 찾은 갯수
		StringBuilder sb = new StringBuilder(); // 발견된 index 저장

		int j = 0; // 연속된 문자 갯수
		for (int i = 0; i < str.length(); i++) { // 시작한 str 위치
			while (j > 0 && str.charAt(i) != pattern.charAt(j)) { // 같은 부분이 없으면 pi를 이요하여 점프
				j = pi[j - 1];
			}
			if (str.charAt(i) == pattern.charAt(j)) { // pattern과 str의 문자가 같을때
				if (j == pattern.length() - 1) { // 같은 문자갯수가 pattern과 같을때
					count++;
					sb.append((i - j + 1) + " "); // index 1부터 시작하기 위해 +1
					j = pi[j]; // 다음 위치를 찾기 위해 점프
				} else {
					j++;
				}
			}
		}

		System.out.println(count);
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String pattern = br.readLine();

		kmp(str, pattern);
	}
}