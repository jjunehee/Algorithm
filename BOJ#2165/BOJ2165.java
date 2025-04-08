import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2165 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] flavorArray = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			flavorArray[i] = Integer.parseInt(br.readLine());
		}
		
		
		
		int[] dp = new int[N+1];
		
		dp[0] = 0;
		dp[1] = flavorArray[1];
		dp[2] = flavorArray[1] + flavorArray[2];
		
		for(int i=3; i<=N; i++) {
			dp[i] = Math.max(dp[i-2] + flavorArray[i], dp[i-3] + flavorArray[i-1] + flavorArray[i]);
			dp[i] = Math.max(dp[i-1], dp[i]);
		}
		System.out.print(dp[N]);
	}
}
