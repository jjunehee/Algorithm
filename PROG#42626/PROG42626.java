
import java.util.*;

class PROG42626 {
	public int solution(int[] scoville, int K) {
		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int num : scoville) {
			pq.add(num);
		}

		int[] pick = new int[2];
		while (!pq.isEmpty() && pq.size() >= 2) {
			pick[0] = pq.poll();
			pick[1] = pq.poll();
			if (pick[0] >= K) {
				break;
			}
			pq.add(pick[0] + (pick[1] * 2));
			answer++;
		}

		if (pq.size() == 1) {
			if (pq.poll() < K) {
				return -1;
			}
		}

		return answer;
	}
}