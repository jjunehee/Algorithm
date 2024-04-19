
class PROG87390 {

	public int[] solution(int n, long left, long right) {

		long x = left / Long.valueOf(n) + Long.valueOf(1);
		long y = left % Long.valueOf(n) + Long.valueOf(1);

		int[] answer = new int[(int) (right - left) + 1];

		int value = 0;
		for (int i = 0; i < right - left + 1; i++) {
			value = (int) Math.max(x, y);
			answer[i] = value;
			y += 1;
			if (y > n) {
				x += 1;
				y = 1;
			}
		}

		return answer;
	}

}