import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010 {
	static int[] pick;
	static int N, M;
	static int[][] dp; // dp[i] : i번째 다리까지 연결된 경우의 수

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dp = new int[N + 1][M + 1];

			for (int j = 1; j <= M; j++) {
				dp[1][j] = j;
			}

			for (int i = 2; i <= N; i++) {
				for (int j = 2; j <= M; j++) {

					for (int k = 1; k < j; k++) {
						dp[i][j] += dp[i - 1][k];
					}
				}
			}
			sb.append(dp[N][M]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
