import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class PROG15486 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 일수
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int[][] consults = new int[N + 2][2];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int period = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			consults[i][0] = period;
			consults[i][1] = value;
		}

		// i일까지 얻는 최대 금액
		int[] dp = new int[N + 2];

		int max = -1;
		for (int i = 1; i <= N+1; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}

			int next = i + consults[i][0];
			int value = consults[i][1];

			if (next < N+2) {
				dp[next] = Math.max(dp[next], max + value);
			}
		}
		
		System.out.println(dp[N+1]);

	}

}
