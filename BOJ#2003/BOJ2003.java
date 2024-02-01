import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003 {
	static int[] pick;
	static int N, M;
	static int[] dp;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		dp[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
		}

		pick = new int[2];
		comb(0, 0);
		System.out.println(ret);

	}

	public static void comb(int idx, int cnt) {

		if (cnt == 2) {
			if (dp[pick[1]] - dp[pick[0]] == M) {
				ret++;
			}
			return;
		}

		for (int i = idx; i <= N; i++) {
			pick[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}
}
