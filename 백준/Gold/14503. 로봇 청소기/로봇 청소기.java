import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // d 에따라   동 서 남 북
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static int N, M;
    public static boolean[][] room;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        // true -> 벽
        room = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                if(st.nextToken().equals("1")) {
                    room[i][j] = true;
                }
            }
        }

        // 청소 여부
        visited = new boolean[N][M];
        int cleanCount = 0;

        // 청소 시작
        while(true) {
            // 현위치 청소
            if(!visited[r][c]) {
                visited[r][c] = true;
                cleanCount++;
            }

            // 주변 4칸 확인
            if(findAround(r, c)) {
                // 청소하지 않은 빈칸이 있을 경우
                d = (d == 0) ? 3 : d - 1;

                int nr = r + dr[d];
                int nc = c + dc[d];
                // 한칸 앞이 청소 안한상태, 빈칸이면
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && !room[nr][nc]) {
                    // 전진
                    r = nr;
                    c = nc;
                }

            } else {
                // 청소하지 않은 빈칸이 없는 경우
                int nr = r - dr[d];
                int nc = c - dc[d];

                // 후진 가능하다면
                if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if(room[nr][nc]) {
                        // 뒤가 벽이면 종료
                        break;
                    } else {
                        // 후진
                        r = nr;
                        c = nc;
                    }
                }
            }
        }

        System.out.println(cleanCount);
    }

    public static boolean findAround(int r, int c) {

        int nr, nc;

        for(int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                continue;
            }

            if (!visited[nr][nc] && !room[nr][nc]) {
                return true;
            }
        }

        return false;
    }
}