class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = StringToSecond(play_time);
		int advTime = StringToSecond(adv_time);

		int[] time = new int[playTime + 2];
		for (String log : logs) {
			String[] split = log.split("-");
			int start = StringToSecond(split[0]);
			int end = StringToSecond(split[1]);

			time[start]++;
			time[end]--;
		}

		int startTime = findStartTime(time, playTime, advTime);

		return secondToString(startTime);
	}

	public int findStartTime(int[] time, int playTime, int advTime) {
		int sum = 0;
		long player = 0;
		for (int i = 0; i < advTime; i++) {
			sum += time[i];
			player += sum;
		}

		long maxPlayer = player;
		int maxPlayerTime = 0;
		for (int i = advTime; i <= playTime; i++) {
			sum += (time[i] - time[i - advTime]);
			player += sum;
			// System.out.println(
			// 	secondToString(i - advTime) + " " + sum + " " + player + " " + time[i] + " " + time[i - advTime]);
			if (player > maxPlayer) {
				maxPlayer = player;
				maxPlayerTime = i - advTime + 1;
			}
		}

		return maxPlayerTime;
	}

	public int StringToSecond(String time) {
		String[] split = time.split(":");
		int hour = Integer.parseInt(split[0]);
		int minute = Integer.parseInt(split[1]);
		int second = Integer.parseInt(split[2]);

		return hour * 3600 + minute * 60 + second;
	}

	public String secondToString(int time) {
		int hour = time / 3600;
		int minute = (time % 3600) / 60;
		int second = (time % 3600) % 60;

		String hourStr = hour < 10 ? "0" + hour : "" + hour;
		String minuteStr = minute < 10 ? "0" + minute : "" + minute;
		String secondStr = second < 10 ? "0" + second : "" + second;

		return hourStr + ":" + minuteStr + ":" + secondStr;
	}
}