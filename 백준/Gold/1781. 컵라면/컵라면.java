import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static class Problem implements Comparable<Problem> {
        int deadLine;
        int noodle;

        public Problem(int deadLine, int noodle) {
            this.deadLine = deadLine;
            this.noodle = noodle;
        }

        @Override
        public int compareTo(Problem o) {
            return Integer.compare(this.deadLine, o.deadLine);
        }

		@Override
		public String toString() {
			return "Problem [deadLine=" + deadLine + ", noodle=" + noodle + "]";
		}
        
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Problem[] problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int deadLine = Integer.parseInt(input[0]);
            int noodle = Integer.parseInt(input[1]);
            problems[i] = new Problem(deadLine, noodle);
        }

        Arrays.sort(problems);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Problem problem : problems) {
            pq.offer(problem.noodle);

            if (problem.deadLine < pq.size()) {
                pq.poll();
            }
        }

        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);
    }
}