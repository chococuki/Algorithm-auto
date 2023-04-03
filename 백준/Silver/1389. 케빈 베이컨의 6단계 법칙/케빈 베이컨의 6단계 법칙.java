import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // line[i][j] : i에서 j로 가는데 걸리는 최소 비용
        int[][] line = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(line[i], Integer.MAX_VALUE);
        }

        // 연결된 노드가 있으면 거리를 1로 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            line[a][b] = 1;
            line[b][a] = 1;
        }

        // 플로이드 와샬 알고리즘을 통해 모든 최단 거리를 구함
        for (int stop = 1; stop <= N; stop++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if(line[start][stop] != Integer.MAX_VALUE 
                            && line[stop][end] != Integer.MAX_VALUE) {
                        line[start][end] = Math.min(line[start][end], line[start][stop] + line[stop][end]);
                    }
                }
            }
        }

        // 케빈 베이컨 수가 가장 작은 사람 찾기
        int min = 0;
        int minval = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int tmp = 0;
            for (int j = 1; j <= N; j++) {
                if(i!=j) tmp+=line[i][j];
            }
            if(tmp < minval) {
                min = i;
                minval = tmp;
            }
        }

        System.out.println(min);
    }
}