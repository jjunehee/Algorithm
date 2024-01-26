import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

	static final int R = 0;
	static final int G = 1;
	static final int B = 2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] RGB = new int[N + 1][N];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				RGB[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][R] = Math.min(dp[i - 1][G], dp[i - 1][B]) + RGB[i][R];
			dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + RGB[i][G];
			dp[i][B] = Math.min(dp[i - 1][R], dp[i - 1][G]) + RGB[i][B];
		}

		System.out.println(Math.min(dp[N][2], Math.min(dp[N][0], dp[N][1])));
	}

}
