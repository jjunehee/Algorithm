import java.util.*;

class PROG76502 {

	public int solution(String s) {
		int answer = 0;

		for (int start = 0; start < s.length(); start++) {

			int cnt = 0;
			int i = start;
			Queue<Character> q = new LinkedList<>();
			while (++cnt <= s.length()) {
				q.add(s.charAt(i));
				i = (i + 1) % s.length();
			}

			if (test(q)) {
				answer++;
			}

		}

		return s.length() == 1 ? 0 : answer;
	}

	boolean test(Queue<Character> q) {

		Stack<Character> stack = new Stack<>();

		while (!q.isEmpty()) {
			char c = q.poll();
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
			} else if (stack.size() == 0) {
				return false;
			} else if (c == '}') {
				if (stack.pop() != '{') {
					return false;
				}
			} else if (c == ']') {
				if (stack.pop() != '[') {
					return false;
				}
			} else if (c == ')') {
				if (stack.pop() != '(') {
					return false;
				}
			}
		}

		if (stack.size() > 0) {
			return false;
		}

		return true;
	}
}
