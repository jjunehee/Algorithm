
import java.util.*;

class PROG92341 {
	public int[] solution(int[] fees, String[] records) {

		int standardT = fees[0];
		int standardF = fees[1];
		int perT = fees[2];
		int perF = fees[3];

		Map<String, List<Integer>> map = new TreeMap<>();

		for (int i = 0; i < records.length; i++) {
			String[] strArray = records[i].split(" ");

			String[] timeArray = strArray[0].split(":");
			int time = Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);

			List<Integer> list = map.getOrDefault(strArray[1], new ArrayList<>());
			list.add(time);
			map.put(strArray[1], list);
		}

		int[] answer = new int[map.size()];

		int idx = 0;
		for (Map.Entry<String, List<Integer>> ret : map.entrySet()) {
			List<Integer> valueList = ret.getValue();

			if (valueList.size() % 2 != 0) {
				valueList.add(1439);
			}

			int time = 0;
			for (int i = 0; i <= valueList.size() - 2; i += 2) {
				time += (valueList.get(i + 1) - valueList.get(i));
			}

			double retFee = 0;
			if (time > standardT) {
				retFee = standardF + Math.ceil((time - standardT) / (double) perT) * perF;
			} else {
				retFee = standardF;
			}
			answer[idx++] = (int) retFee;
		}

		return answer;
	}
}