import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K;
	static int[] visited = new int[100001];	//방문한 노드 
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        System.out.println(bfs());
    }
    
    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N); // 시작 노드를 큐에 추가
        visited[N] = 1; // 시작 노드 방문 처리
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == K) { // 목표 노드에 도달하면 레벨(시간) 반환
                return visited[current] - 1;
            }
            // 이웃 노드 추가
            if (current - 1 >= 0 && visited[current - 1] == 0) {
                queue.offer(current - 1);
                visited[current - 1] = visited[current] + 1;
            }
            if (current + 1 <= 100000 && visited[current + 1] == 0) {
                queue.offer(current + 1);
                visited[current + 1] = visited[current] + 1;
            }
            if (current * 2 <= 100000 && visited[current * 2] == 0) {
                queue.offer(current * 2);
                visited[current * 2] = visited[current] + 1;
            }
        }
        return -1; // 목표 노드에 도달하지 못한 경우 -1 반환
    }
}