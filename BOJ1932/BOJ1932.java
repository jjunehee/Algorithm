import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {

	public static void main(String[] args) throws IOException {

		int[][] map = new int[501][501];
		int[][] dp = new int[501][501];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][1] = map[1][1];

		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + map[i][j];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int j = 1; j <= n; j++) {
			max = Math.max(dp[n][j], max);
		}

		System.out.println(max);
	}
}
