import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        String[][] map = new String[N][M];
        for(int n = 0; n < N; n++) {
            map[n] = br.readLine().split("");
        }

        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                if(map[n][m].equals("U") || map[n][m].equals("R") ||
                        map[n][m].equals("D") || map[n][m].equals("L")) {
                    map[n][m] = isNewRoute(n, m, map);

                }
            }
        }

        int answer = 0;
        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                if(map[n][m].equals("1")) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static String isNewRoute(int n, int m, String[][] map) {
        int newN = -1, newM = -1;

        if(map[n][m].equals("U")) {
            newN = n - 1;
            newM = m;
        } else if(map[n][m].equals("R")) {
            newN = n;
            newM = m + 1;
        } else if(map[n][m].equals("D")) {
            newN = n + 1;
            newM = m;
        } else if(map[n][m].equals("L")) {
            newN = n;
            newM = m - 1;
        } else if(map[n][m].equals("0")) {
            return "0";
        } else if(map[n][m].equals("1")) {
            return "1";
        } else if(map[n][m].equals("-1")) {
            return "0";
        }

        if(newN >= 0 && newN < N && newM >= 0 && newM < M) {
            map[n][m] = "-1";
            map[n][m] = isNewRoute(newN, newM, map);
            return map[n][m];
        } else {
            return "1";
        }
    }
}