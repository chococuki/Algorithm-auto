import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> prices = new PriorityQueue<>(); // 모든 가격
        PriorityQueue<Node> pricesNotZero = new PriorityQueue<>(); // 0숫자 제외
        for (int i = 0; i < N; i++) {
            Node node = new Node(i, Integer.parseInt(st.nextToken()));
            prices.add(node);
            if(i != 0) {
                pricesNotZero.add(node);
            }
        }

        int M = Integer.parseInt(br.readLine());

        // 가장 싼 숫자로 최대한 구매(최대 자리수)
        // 첫 숫자가 0이면 0 아닌 수 하나 추가
        int[] number;
        if(prices.peek().number == 0) {
            if(pricesNotZero.isEmpty()) {
                number = new int[1];
                number[0] = 0;
            } else {
            	M -= pricesNotZero.peek().price;
            	
            	if(M < 0) {
            		number = new int[1];
                    number[0] = 0;
            	} else {
	                Node minNode = prices.peek();
	                int minNumberCount = M / minNode.price;
	                number = new int[minNumberCount + 1];
	                number[0] = pricesNotZero.peek().number;
	
	                for (int i = 1; i < minNumberCount; i++) {
	                    number[i] = minNode.number;
	                }
	                
	                M -= minNode.price * minNumberCount;
	
	                changeNumber(number, prices, M, pricesNotZero.peek().price, prices.peek().price);
            	}
            }


        } else {
            Node minNode = prices.peek();
            int minNumberCount = M / minNode.price;
            number = new int[minNumberCount];

            Arrays.fill(number, minNode.number);

            M -= minNode.price * minNumberCount;

            changeNumber(number, prices, M, minNode.price, -1);
        }

        StringBuilder sb = new StringBuilder();
        for(int n : number) {
            sb.append(n);
        }

        System.out.println(sb);
    }

    public static void changeNumber(int[] number, PriorityQueue<Node> prices, int M, int price, int zeroPrice) {
        if(M == 0) {
            return;
        }

        boolean change = true;
        int index = 0;
        // 첫 자리가 0일때
        if(zeroPrice != -1) {
            PriorityQueue<Node> tmpQ = new PriorityQueue<>(prices);

            int tmpPrice = M + price;
            int p = 0;  // 차액
            while(!tmpQ.isEmpty() && index < number.length) {
                Node node = tmpQ.poll();

                if(number[index] < node.number && tmpPrice >= node.price) {
                    change = true;
                    p = node.price - price;
                    number[index] = node.number;
                }
            }
            
            index++;
            M -= p;
            
            while(M > 0 && change) {
	            change = false;
	            tmpQ = new PriorityQueue<>(prices);
	
	            tmpPrice = M + zeroPrice;
	            p = 0;  // 차액
	            while(!tmpQ.isEmpty() && index < number.length) {
	                Node node = tmpQ.poll();
	
	                if(number[index] < node.number && tmpPrice >= node.price) {
	                    change = true;
	                    p = node.price - zeroPrice;
	                    number[index] = node.number;
	                }
	            }
	            index++;
	            M -= p;
	        }
        // 첫 자리가 0이 아닐때
        } else {
        	while(M > 0 && change) {
	            change = false;
	            PriorityQueue<Node> tmpQ = new PriorityQueue<>(prices);
	
	            int tmpPrice = M + price;
	            int p = 0;  // 차액
	            while(!tmpQ.isEmpty() && index < number.length) {
	                Node node = tmpQ.poll();
	
	                if(number[index] < node.number && tmpPrice >= node.price) {
	                    change = true;
	                    p = node.price - price;
	                    number[index] = node.number;
	                }
	            }
	
	            index++;
	            M -= p;
	        }
        }
        
        
    }

    public static class Node implements Comparable<Node> {
        int number;
        int price;

        public Node(int number, int price) {
            this.number = number;
            this.price = price;
        }

        public int compareTo(Node n) {
            if(this.price == n.price) {
                return Integer.compare(n.number, this.number);
            }
            return Integer.compare(this.price, n.price);
        }
    }
}