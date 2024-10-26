
class PROG12913 {

	static int[][] dp;

	int solution(int[][] land) {
		int answer = 0;

		dp = new int[land.length][land[0].length];

		for (int j = 0; j < 4; j++) {
			dp[0][j] = land[0][j];
		}

		for (int layer = 1; layer < land.length; layer++) {
			for (int j = 0; j < 4; j++) {
				for (int z = 0; z < 4; z++) {
					if (j != z) {
						if (dp[layer][j] < dp[layer - 1][z] + land[layer][j]) {
							dp[layer][j] = dp[layer - 1][z] + land[layer][j];
						}
					}
				}
			}
		}

		for (int j = 0; j < 4; j++) {
			answer = Math.max(dp[land.length - 1][j], answer);
		}

		return answer;
	}
}