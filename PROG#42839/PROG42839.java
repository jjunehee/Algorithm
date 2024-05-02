
import java.util.*;

class PROG42839 {
	static boolean[] visited;
	static Set<Integer> set = new HashSet<>();

	public int solution(String numbers) {
		visited = new boolean[numbers.length()];
		permu(numbers, "", 0);
		return set.size();
	}

	public void permu(String numbers, String str, int len) {

		if (len > numbers.length()) {
			return;
		}

		if (len > 0 && isPrime(Integer.parseInt(str))) {
			set.add(Integer.parseInt(str));
		}

		for (int i = 0; i < numbers.length(); i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			permu(numbers, str + numbers.charAt(i), len + 1);
			visited[i] = false;
		}
	}

	public boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}