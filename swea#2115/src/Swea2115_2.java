import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2115_2 {

	static int[][] map;
	static int N, M, C;
	static int[][] maxSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			maxSum = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			makeMax();
			int ret = comb();

			sb.append(ret).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static int comb() {

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				int aBenefit = maxSum[i][j];

				int bBenefit = 0;

				for (int y = j + M; y <= N - M; y++) {
					bBenefit = Math.max(bBenefit, maxSum[i][y]);
				}

				for (int x = i + 1; x < N; x++) {
					for (int y = 0; y <= N - M; y++) {
						bBenefit = Math.max(bBenefit, maxSum[x][y]);
					}
				}

				max = Math.max(max, aBenefit + bBenefit);
			}
		}

		return max;
	}

	private static void makeMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				calc(i, j, 0, 0, 0);
			}
		}
	}

	public static void calc(int x, int y, int cnt, int cSum, int bSum) {
		if (cSum > C) {
			return;
		}
		if (cnt == M) {
			if (maxSum[x][y - M] < bSum) {
				maxSum[x][y - M] = bSum;
			}
			return;
		}

		calc(x, y + 1, cnt + 1, cSum, bSum);
		calc(x, y + 1, cnt + 1, cSum + map[x][y], bSum + map[x][y] * map[x][y]);
	}

}
