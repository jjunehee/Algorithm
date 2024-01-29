import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12852 {

	static int[] pick;
	static int[] result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		;

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = 0;

		if (N >= 2) {
			dp[2] = 1;
		}
		if (N >= 3) {
			dp[3] = 1;
		}

		for (int i = 4; i <= N; i++) {
			if (i % 2 == 0) {
				dp[i] = dp[i / 2] + 1;
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}

		pick = new int[dp[N]];
		result = new int[dp[N]];
		permu(0, N, dp[N]);

		System.out.println(dp[N]);
		StringBuilder sb = new StringBuilder();
		sb.append(N + " ");
		for(int num : result) {
			sb.append(num + " ");
		}
		System.out.println(sb.toString());
	}

	public static void permu(int cnt, int value, int limit) {

		if (cnt == limit) {
			if (value == 1) {
				for (int i = 0; i < limit; i++) {
					result[i] = pick[i];
				}
			}
			return;
		}

		if (value % 2 == 0) {
			pick[cnt] = value / 2;
			permu(cnt + 1, value / 2, limit);
		}

		if (value % 3 == 0) {
			pick[cnt] = value / 3;
			permu(cnt + 1, value / 3, limit);
		}

		pick[cnt] = value - 1;
		permu(cnt + 1, value - 1, limit);

	}
}
