import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		Set<Character> keys = new HashSet<>();
		for (int n = 0; n < N; n++) {
			String[] words = br.readLine().split(" ");

			StringBuilder sb = new StringBuilder();
			boolean find = false;
			for (String word : words) {
				char cha = word.charAt(0);
				if (!keys.contains((char)(cha >= 97 ? cha - 32 : cha)) && !find) {
					find = true;
					keys.add((char)(cha >= 97 ? cha - 32 : cha));
					sb.append("[").append(word.charAt(0)).append("]").append(word.substring(1)).append(" ");
				} else {
					sb.append(word).append(" ");
				}
			}

			if (find) {
				bw.write(sb.toString());
				bw.write("\n");
				continue;
			} else {
				sb = new StringBuilder();
			}

			for (String word : words) {
				if (find) {
					sb.append(word).append(" ");
					continue;
				}

				for (char cha : word.toCharArray()) {
					if (!keys.contains((char)(cha >= 97 ? cha - 32 : cha)) && !find) {
						find = true;
						keys.add((char)(cha >= 97 ? cha - 32 : cha));
						sb.append("[").append(cha).append("]");
					} else {
						sb.append(cha);
					}

				}
				sb.append(" ");
			}

			bw.write(sb.toString());
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}