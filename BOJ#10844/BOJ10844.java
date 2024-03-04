import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int mod = 1000000000;
		int[][] dp = new int[N + 1][10];

		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {

			for (int j = 0; j < 10; j++) {

				if (j == 0) {
					dp[i][j] = dp[i - 1][1] % mod;
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][8] % mod;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				}
			}
		}

		long ret = 0;
		for (int j = 0; j < 10; j++) {
			ret += dp[N][j];
		}
		
		System.out.println(ret % mod);
	}
}
