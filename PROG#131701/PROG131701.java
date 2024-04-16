import java.util.*;

public class PROG131701 {
	static List<Node>[] graphInfo;

	public int solution(int[] elements) {
		graphInfo = new ArrayList[elements.length + 1];
		for (int i = 1; i <= elements.length; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		int idx = 1;
		for (int value : elements) {
			graphInfo[idx].add(new Node(value, idx + 1 > elements.length ? 1 : ++idx));
		}

		Set<Integer> set = new HashSet<>();
		for (int length = 1; length <= elements.length; length++) { // length
			for (int i = 1; i <= elements.length; i++) { // start
				set.add(search(i, length));
			}
		}

		return set.size();
	}

	int search(int start, int length) {
		int ret = 0;
		int idx = start;
		for (int i = 0; i < length; i++) {
			ret += graphInfo[idx].get(0).value;
			idx = graphInfo[idx].get(0).next;
		}

		return ret;
	}

	public class Node {
		int value;
		int next;

		public Node(int value, int next) {
			this.value = value;
			this.next = next;
		}
	}
}
