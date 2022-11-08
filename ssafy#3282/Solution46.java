import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution46 {
	static int[][] dp;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] V = new int[N + 1];
			int[] C = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}

			dp = new int[N + 1][K + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if (V[i] > j) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - V[i]] + C[i]);
					}
				}
			}

			answer[t] = dp[N][K];
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}
	}
}
