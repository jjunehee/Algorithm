
import java.util.*;

class PROG42587 {
	public int solution(int[] priorities, int location) {
		int answer = 0;

		PriorityQueue<Element> pq = new PriorityQueue<>();
		Queue<Element> q = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			q.add(new Element(i, priorities[i]));
			pq.add(new Element(i, priorities[i]));
		}

		while (!pq.isEmpty()) {
			answer++;
			Element e = pq.poll();
			int topPrior = e.prior;
			while (true) {
				Element now = q.poll();
				if (now.prior == topPrior) {
					if (now.location == location) {
						return answer;
					} else {
						break;
					}
				} else {
					q.add(now);
				}
			}
		}
		return -1;
	}

	class Element implements Comparable<Element> {
		int location;
		int prior;

		public Element(int location, int prior) {
			this.location = location;
			this.prior = prior;
		}

		@Override
		public int compareTo(Element o) {
			return o.prior - this.prior;
		}
	}
}