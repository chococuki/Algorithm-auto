import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static String[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        map = new String[N][M];
        visited = new boolean[N][M];
        for(int n = 0; n < N; n++) {
            map[n] = br.readLine().split("");
        }

        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                if(!visited[n][m]) {
                    map[n][m] = isNewRoute(n, m);
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

    public static String isNewRoute(int n, int m) {
        int newN = -1, newM = -1;

        if(!visited[n][m]) {
            visited[n][m] = true;

            if (map[n][m].equals("U")) {
                newN = n - 1;
                newM = m;
            } else if (map[n][m].equals("R")) {
                newN = n;
                newM = m + 1;
            } else if (map[n][m].equals("D")) {
                newN = n + 1;
                newM = m;
            } else if (map[n][m].equals("L")) {
                newN = n;
                newM = m - 1;
            }
        } else {
            if(map[n][m].equals("1")) {
                return "1";
            } else {
                map[n][m] = "0";
                return "0";
            }
        }

        if(newN >= 0 && newN < N && newM >= 0 && newM < M) {
            map[n][m] = isNewRoute(newN, newM);
            return map[n][m];
        } else {
            map[n][m] = "1";
            return "1";
        }
    }
}