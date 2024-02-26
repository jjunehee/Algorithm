
// 가장 큰 증가하는 부분 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N + 1];
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i] = numbers[i];
		}

		dp[1] = numbers[1];
		int max = numbers[1];
		for (int i = 2; i <= N; i++) {
			for(int j=1; j<i; j++) {
				if(numbers[j] < numbers[i]) {
					dp[i] = Math.max(dp[j] + numbers[i], dp[i]);
					max = Math.max(max, dp[i]);
				}
			}
		}
		
		System.out.println(max);

	}
}
