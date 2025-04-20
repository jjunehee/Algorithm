
import java.util.*;
import java.io.*;

public class BOJ2491 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] dp = new int[N]; // dp[i] : i번째까지 증가하는 부분수열의 길이 최대
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1; // dp[i] 초기화
		}
		
		for(int i=1; i<N; i++) {
			if(numbers[i-1] < numbers[i]) {
				dp[i] = Math.max(dp[i], dp[i-1] + 1);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.print(max);
	}
}
