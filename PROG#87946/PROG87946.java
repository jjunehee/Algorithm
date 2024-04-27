


class PROG87946 {

	static int max = Integer.MIN_VALUE;
	static boolean[] visited;

	public int solution(int k, int[][] dungeons) {

		visited = new boolean[dungeons.length];
		permu(k, 0, dungeons);

		return max;
	}

	public void permu(int hp, int cnt, int[][] dungeons) {

		max = Math.max(max, cnt);

		for (int i = 0; i < dungeons.length; i++) {
			if (visited[i] || hp < dungeons[i][0]) {
				continue;
			}
			visited[i] = true;
			permu(hp - dungeons[i][1], cnt + 1, dungeons);
			visited[i] = false;
		}

	}
}
