
import java.util.*;

class PROG42888 {
	public String[] solution(String[] record) {

		Map<String, String> infoMap = new HashMap<>();

		List<ChatInfo> chat = new ArrayList<>();

		for (String str : record) {
			String[] splitStr = str.split(" ");
			String order = splitStr[0];
			String id = splitStr[1];
			String name = null;
			if (!"Leave".equals(order)) {
				name = splitStr[2];
			}

			if ("Enter".equals(order)) { // 입장!
				infoMap.put(id, name);
				chat.add(new ChatInfo(id, "님이 들어왔습니다."));
			} else if ("Leave".equals(order)) { // 퇴장!
				chat.add(new ChatInfo(id, "님이 나갔습니다."));
			} else if ("Change".equals(order)) { // 변경!
				infoMap.put(id, name);
			}

		}

		String[] answer = new String[chat.size()];

		int idx = 0;
		for (ChatInfo info : chat) {
			answer[idx++] = infoMap.get(info.id) + info.message;
		}

		return answer;
	}

	class ChatInfo {
		String id;
		String message;

		public ChatInfo(String id, String message) {
			this.id = id;
			this.message = message;
		}
	}
}
