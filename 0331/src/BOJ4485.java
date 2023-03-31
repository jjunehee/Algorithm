import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ4485 {
	static int[][] map;
	static int[][] dp; // dp[i][j] : [i][j]에 도착하는 최소 루피값

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			int N = Integer.parseInt(br.readLine());

			

			map = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					
					if(i-1 >=0) {
						dp[i][j] = dp[i-1][j] + map[i][j];
					} else if(i+1 < N) {
						dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + map[i][j]);
					} else if(j-1 >=0) {
						dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + map[i][j]);
					} else if(j+1 < N) {
						dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + map[i][j]);
					}
					
				}
			}
			
			System.out.println(dp[N][N]);

			break;
		}

	}
}
