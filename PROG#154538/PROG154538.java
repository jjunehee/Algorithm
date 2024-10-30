
import java.util.*;

class PROG154538 {

	static int min = Integer.MAX_VALUE;
	static int[] dp;

	public int solution(int x, int y, int n) {
		int answer = 0;

		dp = new int[1000001];
		permu(0, x, y, n);

		answer = min == Integer.MAX_VALUE ? -1 : min;
		return answer;
	}

	public void permu(int cnt, int x, int y, int n) {

		if (dp[x] != 0 && dp[x] < cnt) {
			return;
		}

		if (x > y) {
			return;
		}

		dp[x] = cnt;
		if (x == y) {
			min = Math.min(cnt, min);
			return;
		}

		permu(cnt + 1, x + n, y, n);
		permu(cnt + 1, x * 2, y, n);
		permu(cnt + 1, x * 3, y, n);
	}
}