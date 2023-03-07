import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2115 {
	static int[][] map;
	static int[][] maxSum;
	static int N, M, C;

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

			MakeMax();
			int ret = comb();
			sb.append(ret).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int comb() {

		int max = 0;
		int aBenefit = 0;
		int bBenefit;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				aBenefit = maxSum[i][j];

				// 해당 행 이후

				bBenefit = 0;
				for (int j1 = j + M; j1 <= N - M; j1++) {
					if (bBenefit < maxSum[i][j1])
						bBenefit = maxSum[i][j1];
				}

				// 다음 행부터
				for (int i1 = i + 1; i1 < N; i++) {
					for (int j1 = 0; j1 <= N - M; j1++) {
						if (bBenefit < maxSum[i1][j1])
							bBenefit = maxSum[i1][j1];
					}
				}

				if (max < aBenefit + bBenefit) {
					max = aBenefit + bBenefit;
				}
			}
		}

		return max;

	}

	private static void MakeMax() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				// 부분집합을 활용해서 해당 경우에서 나올 수 있는 최대 경우를 찾는다.
				getMax(i, j, 0, 0, 0);
			}
		}
	}

	private static void getMax(int x, int y, int cnt, int cSum, int bSum) {

		if (cSum > C) {
			return;
		}
		if (cnt == M) {
			if (maxSum[x][y-M] < bSum)
				maxSum[x][y-M] = bSum;
			return;
		}

		getMax(x, y + 1, cnt + 1, cSum + map[x][y], bSum + map[x][y] * map[x][y]);
		getMax(x, y + 1, cnt + 1, cSum, bSum);

	}
}