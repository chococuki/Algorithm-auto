import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon 1041
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        if(N == 2) {
            result = find3min(arr) * 4 + find2min(arr) * 4;
        } else if(N > 2) {
            result = (long) ((Math.pow(N-2, 2) * 5 + (N-2) * 4) * find1min(arr)
                    + find2min(arr) * ((N-2) * 8 + 4)
                    + find3min(arr) * 4);
            //System.out.println((Math.pow(N-2, 2) * 5 + (N-2) * 4) + " " + ((N-2) * 8 + 4) + " " + 4);
            //System.out.println(find3min(arr) + " " + find2min(arr) + " " + find1min(arr));
        } else {
            int maxNum = 0;
            for(int i = 0; i < 6; i++) {
                result += arr[i];
                maxNum = Math.max(maxNum, arr[i]);
            }
            result -= maxNum;
        }
        System.out.println(result);
    }

    private static int find1min(int[] arr) {
        int result = Integer.MAX_VALUE;

        for(int a : arr) {
            result = Math.min(a, result);
        }

        return result;
    }

    private static int find2min(int[] arr) {
        int result = Integer.MAX_VALUE;

        result = Math.min(arr[0]+arr[1], result);
        result = Math.min(arr[0]+arr[2], result);
        result = Math.min(arr[0]+arr[3], result);
        result = Math.min(arr[0]+arr[4], result);
        result = Math.min(arr[1]+arr[5], result);
        result = Math.min(arr[1]+arr[2], result);
        result = Math.min(arr[1]+arr[3], result);
        result = Math.min(arr[2]+arr[4], result);
        result = Math.min(arr[2]+arr[5], result);
        result = Math.min(arr[3]+arr[4], result);
        result = Math.min(arr[3]+arr[5], result);
        result = Math.min(arr[4]+arr[5], result);

        return result;
    }

    private static int find3min(int[] arr) {
        int result = Integer.MAX_VALUE;

        //ABC ABD AEC AED FBC FBD FEC FED

        result = Math.min(arr[0]+arr[1]+arr[2], result);
        result = Math.min(arr[0]+arr[1]+arr[3], result);
        result = Math.min(arr[0]+arr[4]+arr[2], result);
        result = Math.min(arr[0]+arr[4]+arr[3], result);
        result = Math.min(arr[5]+arr[1]+arr[2], result);
        result = Math.min(arr[5]+arr[1]+arr[3], result);
        result = Math.min(arr[5]+arr[4]+arr[2], result);
        result = Math.min(arr[5]+arr[4]+arr[3], result);

        return result;
    }
}