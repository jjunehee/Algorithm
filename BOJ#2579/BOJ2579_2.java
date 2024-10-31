import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 계단 수
		int N = Integer.parseInt(br.readLine());

		int[] stairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		// dp[i] i까지 도착하는데 걸리는 최대 값
		int[] dp = new int[N + 1];

		// 초기화
		dp[0] = 0;
		dp[1] = stairs[1];
		if(N>=2) {
			dp[2] = stairs[1] + stairs[2];
		}

		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[i];
		}
		
		System.out.println(dp[N]);

	}
}
