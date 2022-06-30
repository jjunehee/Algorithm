package src;

import java.util.*;
import java.io.*;

public class Main14501 {

	public static int[] time;
	public static int[] reward;
	public static int N;
	public static int max;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		time = new int[N + 1];
		reward = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			time[i] = T;
			reward[i] = P;
		}
		solve();
		System.out.println(max);
	}

	private static void solve() {
		max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 1; i <= N; i++) {

			while (i <= N) {
				if (i + time[i] > N+1)
					break;
				sum += reward[i];
				i += time[i];
			}
			max = Math.max(max, sum);
		}
	}

}
