
import java.util.*;

class PROG131127 {
	public int solution(String[] want, int[] number, String[] discount) {
		int answer = 0;

		int[] info = new int[want.length];

		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < want.length; i++) {
			map.put(want[i], i);
		}

		for (int i = 0; i < 10; i++) {
			int idx = map.getOrDefault(discount[i], -1);
			if (idx != -1) {
				info[idx]++;
			}
		}

		if (check(number, info)) {
			answer++;
		}

		for (int i = 10; i < discount.length; i++) {

			int idx = map.getOrDefault(discount[i], -1);
			if (idx != -1) {
				info[idx]++;
			}

			idx = map.getOrDefault(discount[i - 10], -1);
			if (idx != -1) {
				info[idx]--;
			}

			if (check(number, info)) {
				answer++;
			}

		}

		return answer;
	}

	boolean check(int[] number, int[] info) {
		for (int j = 0; j < number.length; j++) {
			if (number[j] > info[j]) {
				return false;
			}
		}
		return true;
	}
}