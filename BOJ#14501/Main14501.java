package src;

import java.util.*;
import java.io.*;

public class Main14501 {
	public static int N;
	public static int[] T;
	public static int[] P;
	public static int max;
	public static int[] dp; // i일까지의 최대수익

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		T = new int[N+10];
		P = new int[N+10];
		dp = new int[N+10];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		solve();
		System.out.println(max);

	}

	private static void solve() {
		max = 0;
		for (int i = 1; i <= N + 1; i++) {
			
			dp[i] = Math.max(dp[i], max);
			
			dp[T[i]+i] = Math.max(dp[T[i]+i],P[i]+dp[i]);
			
			max = Math.max(max,dp[i]);
		}
	}
}