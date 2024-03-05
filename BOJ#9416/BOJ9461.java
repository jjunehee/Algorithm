import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 파도반 수열
public class BOJ9461 {

	public static void main(String[] args) throws IOException {

		long[] dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <= 100; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			if(t==T) {
				sb.append(dp[N]);
			} else {
				sb.append(dp[N]).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
