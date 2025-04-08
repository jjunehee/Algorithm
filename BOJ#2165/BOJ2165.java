import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2165 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		int[] flavorArray = new int[N+1];
		for(int i=1; i<=N; i++) {
			flavorArray[i] = Integer.parseInt(br.readLine());
		}
		
		// dp[i] : i 번쨰까지 최대 포도주
		int[] dp = new int[N+1];
		
		// 초기화
		dp[0] = 0;
		dp[1] = flavorArray[1];
		if(N>=2) {
			dp[2] = flavorArray[1] + flavorArray[2];
		}
		
		for(int i=3; i<=N; i++) {
			// i번째를 먹는 경우의 수 2가지
			dp[i] = Math.max(dp[i-2] + flavorArray[i], dp[i-3] + flavorArray[i-1] + flavorArray[i]);
			// i번째를 먹지않는 경우의 수
			dp[i] = Math.max(dp[i-1], dp[i]);
		}
		System.out.print(dp[N]);
	}
}
