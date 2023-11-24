import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public int[] map;
	public List<String> result;
	public Map<Integer, String> data;

	public String[] solution(String[] commands) {
		// 초기화
		map = new int[2501];
		for (int i = 1; i <= 2500; i++) {
			map[i] = i;
		}

		result = new ArrayList<>();
		data = new HashMap<>();

		for (String command : commands) {
			String[] split = command.split(" ");
			String cmd = split[0];

			if (cmd.equals("UPDATE")) {
				if (split.length == 4) {
					int r = Integer.parseInt(split[1]);
					int c = Integer.parseInt(split[2]);

					set(r, c, split[3]);
				} else {
					update(split[1], split[2]);
				}
			} else if (cmd.equals("MERGE")) {
				int r1 = Integer.parseInt(split[1]);
				int c1 = Integer.parseInt(split[2]);
				int r2 = Integer.parseInt(split[3]);
				int c2 = Integer.parseInt(split[4]);

				merge(r1, c1, r2, c2);
			} else if (cmd.equals("UNMERGE")) {
				int r = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);

				unmerge(r, c);
			} else if (cmd.equals("PRINT")) {
				int r = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);

				print(r, c);
			}
		}

		String[] answer = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}

		return answer;
	}

	public int convertToNum(int r, int c) {
		return (r - 1) * 50 + c;
	}

	public void set(int r, int c, String value) {
		int num = convertToNum(r, c);
		int parent = find(num);
		data.put(parent, value);
	}

	public void update(String target, String value) {
		for (int num : data.keySet()) {
			if (data.get(num).equals(target)) {
				data.put(num, value);
			}
		}
	}

	public void merge(int r1, int c1, int r2, int c2) {
		int num1 = convertToNum(r1, c1);
		int num2 = convertToNum(r2, c2);

		if (num1 == num2) {
			return;
		}

		int parent1 = find(num1);
		int parent2 = find(num2);

		if (parent1 == parent2) {
			return;
		}

		if (data.containsKey(parent1) && data.containsKey(parent2)) {
			union(parent1, parent2);
			data.remove(parent2);
		} else if (data.containsKey(parent1)) {
			union(parent1, parent2);
		} else if (data.containsKey(parent2)) {
			union(parent2, parent1);
		} else {
			union(parent1, parent2);
		}
	}

	public void unmerge(int r, int c) {
		int num = convertToNum(r, c);
		int parent = find(num);
		String value = data.get(parent);

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < map.length; i++) {
			if (find(map[i]) == parent) {
				list.add(i);
			}
		}

		for (int i : list) {
			map[i] = i;
		}

		if (value != null) {
			data.remove(parent);
			data.put(num, value);
		}
	}

	public void print(int r, int c) {
		int num = find(convertToNum(r, c));
		result.add(data.getOrDefault(num, "EMPTY"));
	}

	public int find(int num) {
		if (map[num] == num) {
			return num;
		} else {
			return find(map[num]);
		}
	}

	public void union(int num1, int num2) {
		map[num2] = num1;
	}
}