import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    // 정의한 Problem 클래스
    static class Problem implements Comparable<Problem> {
        int deadline;
        int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        // compareTo 메소드를 오버라이드하여 deadline을 기준으로 정렬
        @Override
        public int compareTo(Problem o) {
            return Integer.compare(this.deadline, o.deadline);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 문제 배열 생성
        Problem[] problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int deadline = Integer.parseInt(input[0]);
            int reward = Integer.parseInt(input[1]);
            problems[i] = new Problem(deadline, reward);
        }

        // 문제 배열을 deadline을 기준으로 정렬
        Arrays.sort(problems);

        // reward 값을 저장하기 위한 우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Problem problem : problems) {
            pq.offer(problem.reward);

            // 우선순위 큐의 크기가 deadline보다 크면 가장 작은 reward 값을 제거
            if (problem.deadline < pq.size()) {
                pq.poll();
            }
        }

        // 결과를 계산하기 위한 변수
        long result = 0;
        // 우선순위 큐에서 남아있는 reward 값을 모두 더함
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        // 결과 출력
        System.out.println(result);
    }
}