import java.util.Scanner;

public class CoinTestDP {

	private static int[] dp; // dp[i] : i원을 만드는데 드는 최소 동전 개수

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		dp = new int[n];
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			if (i - 1 >= 0) {
				dp[i] = dp[i - 1] + 1;
			}
			if (i - 4 >= 0) {
				dp[i] = Math.min(dp[i - 4] + 1, dp[i]);
			}
			if (i - 6 >= 0) {
				dp[i] = Math.min(dp[i - 6], dp[i]);
			}
		}
		System.out.println(dp[n]);
	}

}
