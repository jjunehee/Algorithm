
import java.util.*;

class PROG17684 {
	// Map에 (글자,인덱스)로 데이터 저장
	static Map<String, Integer> map = new HashMap<>();

	public int[] solution(String msg) {

		char alphabet = 'A';
		for (int i = 1; i <= 26; i++) {
			map.put(Character.toString(alphabet++), i);
		}

		int idx = 27;
		List<Integer> retList = new ArrayList<>();
		for (int i = 0; i < msg.length(); i++) {
			Pair pair = getStringFromMap(msg, i, msg.length());
			retList.add(map.get(pair.str));
			if (pair.idx == -1) {
				break;
			}
			i = pair.idx;
			map.put(pair.str + msg.charAt(i + 1), idx++);
		}

		int[] answer = new int[retList.size()];

		for (int i = 0; i < retList.size(); i++) {
			answer[i] = retList.get(i);
		}
		return answer;
	}

	Pair getStringFromMap(String msg, int idx, int msgLength) {

		String str = Character.toString(msg.charAt(idx));
		for (int i = idx + 1; i < msgLength; i++) {
			String tmpStr = str;
			tmpStr += msg.charAt(i);
			if (!map.containsKey(tmpStr)) {
				return new Pair(str, i - 1);
			}
			str = tmpStr;
		}

		return new Pair(str, -1);
	}

	class Pair {
		String str;
		int idx;

		public Pair(String str, int idx) {
			this.str = str;
			this.idx = idx;
		}
	}
}