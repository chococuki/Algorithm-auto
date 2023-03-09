import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			arr[i] = tmp;
		}
		
		int frontIndex=0, endIndex=N-1;
		int min = Math.abs(arr[frontIndex]+arr[endIndex]);
		
		int nextFrontIndex = frontIndex;
		int nextEndIndex = endIndex;
		while(nextFrontIndex < nextEndIndex) {
			int tmp = arr[nextFrontIndex]+arr[nextEndIndex];
			if(Math.abs(tmp) < min) {
				min = Math.abs(tmp);
				frontIndex = nextFrontIndex;
				endIndex = nextEndIndex;
			}
			
			if(arr[nextFrontIndex]<0 && arr[nextEndIndex]>0) {
				if(tmp == 0) {
					break;
				} else if(tmp < 0) {
					nextFrontIndex++;
				} else {
					nextEndIndex--;
				}
			} else if(arr[nextFrontIndex] > 0) {
				nextEndIndex--;
			} else if(arr[nextEndIndex] < 0) {
				nextFrontIndex++;
			}
		}
		
		System.out.println(arr[frontIndex]+" "+arr[endIndex]);
		
	}
}