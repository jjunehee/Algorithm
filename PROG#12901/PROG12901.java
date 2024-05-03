
class PROG12901 {
	static int[] endDay = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 30 };
	static String[] day = { "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU" };

	public String solution(int a, int b) {

		String[][] ret = new String[13][32];

		int idx = 0;
		for (int i = 1; i <= 12; i++) {
			int end = endDay[i];
			for (int j = 1; j <= end; j++) {
				ret[i][j] = day[idx];
				idx = (idx + 1) % 7;
			}
		}
		return ret[a][b];
	}
}
