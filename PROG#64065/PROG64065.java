
import java.util.*;

class PROG64065 {
	public int[] solution(String s) {

		int[] answer = {};
		s = s.substring(2, s.length() - 2).replace("},{", "-");
		String[] strArray = s.split("-");

		Arrays.sort(strArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		List<String> list = new ArrayList<>();
		for (int i = 0; i < strArray.length; i++) {

			String[] check = strArray[i].split(",");
			for (int j = 0; j < check.length; j++) {
				if (!list.contains(check[j])) {
					list.add(check[j]);
				}
			}
		}
		answer = new int[list.size()];
		int idx = 0;
		for (String str : list) {
			answer[idx++] = Integer.parseInt(str);
		}
		return answer;
	}
}