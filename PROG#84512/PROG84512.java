
class PROG84512 {
	static int answer = 0;
	static char[] alphabet = { 'A', 'E', 'I', 'O', 'U' };
	static int cnt = 0;
	static boolean found = false;

	public int solution(String word) {
		permu(word, "", 0);
		return answer;
	}

	public void permu(String word, String str, int len) {
		if (len > 5 || found) {
			return;
		}

		if (str.equals(word)) {
			answer = cnt;
			found = true;
			return;
		}

		cnt++;
		for (int i = 0; i < alphabet.length; i++) {
			permu(word, str + alphabet[i], len + 1);
		}

	}
}