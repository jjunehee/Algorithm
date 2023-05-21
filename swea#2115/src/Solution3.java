import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
	static int N, M, C;
	static int[][] map;
	static int[][] maxSum;
	static int ret;

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

			ret = Integer.MIN_VALUE;
			makeMax();
			comb();
			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void comb() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				int aBenefit = maxSum[i][j];

				int bBenefit = 0;
				for (int k = j + M; k <= N - M; k++) {
					bBenefit = maxSum[i][k];
					if (aBenefit + bBenefit > ret) {
						ret = aBenefit + bBenefit;
					}
				}

				for (int a = i + 1; a < N; a++) {
					for (int b = 0; b <= N - M; b++) {
						bBenefit = maxSum[a][b];
						if (aBenefit + bBenefit > ret) {
							ret = aBenefit + bBenefit;
						}
					}
				}
			}
		}
	}

	private static void makeMax() {
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j <= N - M; j++) {
				cal(i, j, 0, 0, 0);
			}
		}
	}

	private static void cal(int i, int j, int cnt, int cSum, int bSum) {

		if (cSum > C) {
			return;
		}
		if (cnt == M) {
			if (maxSum[i][j - M] < bSum) {
				maxSum[i][j - M] = bSum;
			}
			return;
		}

		cal(i, j + 1, cnt + 1, cSum + map[i][j], bSum + map[i][j] * map[i][j]);
		cal(i, j + 1, cnt + 1, cSum, bSum);
	}
}
