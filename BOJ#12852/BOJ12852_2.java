import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12852_2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		int[] path = new int[N + 1];

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			path[i] = i - 1;

			if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
				dp[i] = dp[i / 3] + 1;
				path[i] = i / 3;
			}
			if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
				dp[i] = dp[i / 2] + 1;
				path[i] = i / 2;
			}

		}

		StringBuilder sb = new StringBuilder();
		sb.append(dp[N] + " ").append("\n");
		while (N > 0) {
			sb.append(N + " ");
			N = path[N--];
		}
		System.out.println(sb.toString());
	}

}
