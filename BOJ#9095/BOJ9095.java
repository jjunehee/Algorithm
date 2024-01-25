import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9095 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());


		int[] dp = new int[12];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 11; i++) {
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
