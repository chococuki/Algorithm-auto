import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int testCase = 0; testCase < 3; testCase++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			PriorityQueue<Coin> coins = new PriorityQueue<>();

			int totalPrice = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());

				totalPrice += price * count;
				coins.add(new Coin(price, count));
			}

			if (totalPrice % 2 == 1) {
				System.out.println(0);
				continue;
			}

			Set<Integer> set = new HashSet<>();
			boolean[] canMake = new boolean[totalPrice + 1];

			while (!coins.isEmpty()) {
				Coin now = coins.poll();

				Set<Integer> tmp = new HashSet<>();
				for (int i = 0; i <= now.count; i++) {
					int price = now.price * i;

					if (price > totalPrice / 2) {
						continue;
					}

					tmp.add(price);

					canMake[price] = true;

					for (int last : set) {
						int newPrice = last + price;

						if (newPrice > totalPrice / 2) {
							continue;
						}

						if (!canMake[newPrice]) {
							canMake[newPrice] = true;
							tmp.add(newPrice);
						}
					}
				}
				set = tmp;
			}

			System.out.println(canMake[totalPrice / 2] ? 1 : 0);
		}

	}

	public static class Coin implements Comparable<Coin> {
		int price;
		int count;

		public Coin(int price, int count) {
			this.price = price;
			this.count = count;
		}

		@Override
		public int compareTo(Coin o) {
			return Integer.compare(o.price, this.price);
		}
	}
}