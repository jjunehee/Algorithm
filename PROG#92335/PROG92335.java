
class PROG92335 {

	public int solution(int n, int k) {
		int answer = 0;

		String[] numStr = Integer.toString(n, k).split("0");
		for (int i = 0; i < numStr.length; i++) {
			if (numStr[i].length() == 0) {
				continue;
			}
			if (isPrime(Long.parseLong(numStr[i]))) {
				answer++;
			}
		}

		return answer;
	}

	public boolean isPrime(long n) {
		if (n <= 1) {
			return false;
		}

		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
