
import java.util.*;

class PROG17677 {
	public int solution(String str1, String str2) {
		List<String> str1List = new ArrayList<>();
		List<String> str2List = new ArrayList<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			if (check(str1.substring(i, i + 2).toLowerCase())) {
				str1List.add(str1.substring(i, i + 2).toLowerCase());
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			if (check(str2.substring(i, i + 2).toLowerCase())) {
				str2List.add(str2.substring(i, i + 2).toLowerCase());
			}
		}

		Map<String, Integer> map1 = new HashMap<>();
		for (String str : str1List) {
			map1.compute(str, (key, value) -> value == null ? 1 : value + 1);
		}

		Map<String, Integer> map2 = new HashMap<>();
		for (String str : str2List) {
			map2.compute(str, (key, value) -> value == null ? 1 : value + 1);
		}

		int cnt1 = 0;
		for (Map.Entry<String, Integer> entry : map1.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (map2.containsKey(key)) {
				cnt1 += Math.min(value, map2.get(key));
			}
		}

		int cnt2 = str1List.size() + str2List.size();
		for (Map.Entry<String, Integer> entry : map1.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (map2.containsKey(key)) {
				cnt2 -= Math.min(value, map2.get(key));
			}
		}

		double jarka = 0;
		if (cnt1 == 0 && cnt2 == 0) {
			jarka = 1;
		} else {
			jarka = ((double) cnt1 / (double) cnt2);
		}

		return (int) (jarka * 65536);
	}

	boolean check(String str) {
		char a = str.charAt(0);
		char b = str.charAt(1);
		if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
			return true;
		}
		return false;
	}
}