
import java.util.*;
import java.io.*;

public class BOJ11053 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] dp = new int[N]; // dp[i] : i 번쨰까지의 최대 증가수열 길이
		dp[0] = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());	
		}
		
		for(int i=1; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(numbers[i] > numbers[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
		
	}
}
