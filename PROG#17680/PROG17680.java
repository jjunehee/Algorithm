
import java.util.*;

class PROG17680 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();

		if (cacheSize == 0) {
			return cities.length * 5;
		}

		for (int i = 0; i < cities.length; i++) {
			String str = cities[i].toLowerCase();
			if (map.getOrDefault(str, -1) != -1) { // hit
				map.put(str, 1);
				answer++;
				list.remove(str);
				list.add(str);
			} else { // miss
				map.put(str, 1);
				answer += 5;
				list.add(str);
				if (list.size() > cacheSize) {
					map.remove(list.get(0));
					list.remove(0);
				}
			}
		}

		return answer;
	}
}