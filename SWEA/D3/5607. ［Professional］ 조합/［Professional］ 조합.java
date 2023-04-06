import java.util.Scanner;

public class Solution {
    static final int MOD = 1234567891;

    private static long nCr(long N, long R) {
        long numerator = factorial(N);
        long denominator = (factorial(R) * factorial(N - R)) % MOD;
        long inverseDenominator = pow(denominator, MOD - 2); // 페르마의 소정리 이용
        return (numerator * inverseDenominator) % MOD;
    }

    private static long factorial(long n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % MOD;
        }
        return result;
    }

    private static long pow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % MOD;
            }
            a = (a * a) % MOD;
            b /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            long N = sc.nextLong();
            long R = sc.nextLong();

            System.out.printf("#%d %d\n", t, nCr(N, R));
        }

        sc.close();
    }
}