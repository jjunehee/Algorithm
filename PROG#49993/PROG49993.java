
import java.util.*;

class PROG49993 {
	public int solution(String skill, String[] skill_trees) {
		int answer = 0;

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < skill.length(); i++) {
			char c = skill.charAt(i);
			map.put(c, i);
		}

		for (String str : skill_trees) {

			boolean[] isActivated = new boolean[skill.length()];

			boolean isSuccess = true;
			int check = 0;

			outerLoop: for (int i = 0; i < str.length(); i++) {
				int search = map.getOrDefault(str.charAt(i), -1);

				if (search != -1) {
					if (search - 1 >= 0) {
						if (!isActivated[search - 1] || check > search) {
							isSuccess = false;
							break outerLoop;
						}
					}
					isActivated[search] = true;
					check = search;
				}
			}

			if (isSuccess) {
				answer++;
			}

		}

		return answer;
	}
}