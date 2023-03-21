import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1]; // 최소 획수
        int[] prev = new int[n+1]; // 경로 저장
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0 && dp[i/3] + 1 < dp[i]) {
                dp[i] = dp[i/3] + 1;
                prev[i] = i/3;
            }
            if (i % 2 == 0 && dp[i/2] + 1 < dp[i]) {
                dp[i] = dp[i/2] + 1;
                prev[i] = i/2;
            }
            if (dp[i-1] + 1 < dp[i]) {
                dp[i] = dp[i-1] + 1;
                prev[i] = i-1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]+"\n");
        while(n>0) {
        	sb.append(n+" ");
        	n = prev[n];
        }
        
        System.out.println(sb.toString());
    }
}