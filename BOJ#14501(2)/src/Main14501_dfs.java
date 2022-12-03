package src;

import java.util.*;
import java.io.*;

public class Main14501_dfs {
	public static int N;
	public static int[] T;
	public static int[] P;
	public static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		
		T = new int[N + 10];
		P = new int[N + 10];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			P[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
			
		}

		solve_dfs(1, 0);
		System.out.println(max);

	}

	private static void solve_dfs(int i, int reward) {

		if (i > N) {
			max = Math.max(max, reward);
			return;
		}

		if (i + T[i] <= N + 1)
			solve_dfs(i + T[i], reward + P[i]);
		
		solve_dfs(i + 1, reward);
	}

}
