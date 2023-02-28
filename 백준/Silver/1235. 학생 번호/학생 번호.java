import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> set;
		StringBuilder sb;
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i]=br.readLine();
		}
		
		for(int l=1; l<=arr[0].length(); l++) {
			set = new LinkedHashSet<>();
			boolean finish = true;
			for(int i=0; i<N; i++) {
				sb = new StringBuilder();
				String[] str = arr[i].split("");
				for(int k=arr[0].length()-1; k>=arr[0].length()-l; k--) {
					sb.append(str[k]);
				}
				set.add(sb.toString());
				if(set.size() != (i+1)) {
					finish = false;
					break;
				}
			}
			if(finish) {
				System.out.println(l);
				break;
			}
		}
	}
}
