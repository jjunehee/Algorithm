
import java.util.*;
import java.io.*;

public class BOJ2491 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] increaseDP = new int[N]; // increaseDP[i] : i번째까지 증가하는 부분수열의 길이 최대
		int[] decreaseDP = new int[N]; // decreaseDP[i] : i번쨰까지 감소하는 부분수열의 길이 최대
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			increaseDP[i] = 1; // dp[i] 초기화
			decreaseDP[i] = 1;
		}
		
		for(int i=1; i<N; i++) {
			if(numbers[i-1] <= numbers[i]) {
				increaseDP[i] = increaseDP[i-1] + 1;
			} 
			if (numbers[i-1] >= numbers[i]) {
				decreaseDP[i] = decreaseDP[i-1] + 1;
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max,Math.max(increaseDP[i], decreaseDP[i]));

		}
		System.out.print(max);
	}
}
