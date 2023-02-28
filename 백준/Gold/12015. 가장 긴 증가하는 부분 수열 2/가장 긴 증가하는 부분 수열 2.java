import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		List<Integer> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		list.add(0);
		for(int num:arr) {
			if(list.get(list.size()-1)<num) {
				list.add(num);
			} else {
				int left = 0;
                int right = list.size()-1;
                int mid = 0;

                while (left < right) {
                    mid = (left + right) / 2;
                    if(list.get(mid) < num) {
                        left = mid + 1;
                    }else {
                        right = mid;
                    }
                }
                list.set(right, num);
			}
		}
		System.out.println(list.size()-1);
	}
}