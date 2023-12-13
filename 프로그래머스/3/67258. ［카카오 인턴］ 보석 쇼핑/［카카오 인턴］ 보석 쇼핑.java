import java.util.*;

class Solution {
    public static int[] solution(String[] gems) {
		Set<String> gemSet = new HashSet<>();
		gemSet.addAll(Arrays.asList(gems));

		int gemCount = gemSet.size();

		Map<String, Integer> gemCountMap = new HashMap<>();
		for (String gem : gemSet) {
			gemCountMap.put(gem, 0);
		}

		int count = 1;
		int start = 0, end = 0;
		int minSize = gems.length + 1;
		int[] answer = new int[2];
		gemCountMap.put(gems[0], 1);

		while (true) {
			if (count == gemCount) {
				while (true) {
					if (gemCountMap.get(gems[end]) != 1) {
						gemCountMap.put(gems[end], gemCountMap.get(gems[end]) - 1);
						end++;
					} else {
						break;
					}
				}

				if (minSize > start - end) {
					minSize = start - end;
					answer[0] = end + 1;
					answer[1] = start + 1;
					gemCountMap.put(gems[end], gemCountMap.get(gems[end]) - 1);
					end++;
					count--;
				}
			}

			start++;

			if (start >= gems.length) {
				break;
			} else {
				if (gemCountMap.get(gems[start]) == 0) {
					count++;
				}

				gemCountMap.put(gems[start], gemCountMap.get(gems[start]) + 1);
			}

		}

		return answer;
	}
}